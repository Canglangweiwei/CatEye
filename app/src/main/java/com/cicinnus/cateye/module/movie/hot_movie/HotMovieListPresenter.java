package com.cicinnus.cateye.module.movie.hot_movie;

import android.app.Activity;

import com.cicinnus.cateye.tools.ErrorHanding;
import com.cicinnus.retrofitlib.base.BaseMVPPresenter;
import com.cicinnus.retrofitlib.rx.SchedulersCompat;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Cicinnus on 2017/1/24.
 */

public class HotMovieListPresenter extends BaseMVPPresenter<HotMovieListContract.IHotMovieListView>
        implements HotMovieListContract.IHotMoviePresenter {

    private final HotMovieListManager hotMovieListManager;

    public HotMovieListPresenter(Activity activity, HotMovieListContract.IHotMovieListView view) {
        super(activity, view);
        hotMovieListManager = new HotMovieListManager();
    }

    @Override
    public void getHotMovieList(int limit) {
        mView.showLoading();
        addSubscribeUntilDestroy(hotMovieListManager.getHotMovieList(limit)
                .compose(SchedulersCompat.<HotMovieListBean>applyIoSchedulers())
                .subscribe(new Consumer<HotMovieListBean>() {
                    @Override
                    public void accept(@NonNull HotMovieListBean hotMovieListBean) {
                        mView.addMovieIds(hotMovieListBean.getData().getMovieIds());
                        mView.addHotMovieList(hotMovieListBean.getData().getHot());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) {
                        mView.showError(ErrorHanding.handleError(throwable));

                    }
                }, new Action() {
                    @Override
                    public void run() {
                        mView.showContent();
                    }
                }));
    }

    @Override
    public void getMoreHotMovieList(int headline, final String movieIds) {
        addSubscribeUntilDestroy(hotMovieListManager.getMoreMovieList(headline, movieIds)
                .compose(SchedulersCompat.<List<HotMovieListBean.DataBean.HotBean>>applyIoSchedulers())
                .subscribe(new Consumer<List<HotMovieListBean.DataBean.HotBean>>() {
                    @Override
                    public void accept(@NonNull List<HotMovieListBean.DataBean.HotBean> hotBeen) {
                        mView.addMoreMovies(hotBeen);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) {
                        mView.loadMoreError();
                    }
                }, new Action() {
                    @Override
                    public void run() {
                        mView.loadMoreCompleted();
                    }
                }));
    }
}
