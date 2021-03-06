package com.cicinnus.cateye.module.movie.wait_movie.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cicinnus.cateye.R;
import com.cicinnus.cateye.module.movie.movie_detail.MovieDetailActivity;
import com.cicinnus.cateye.module.movie.wait_movie.bean.ExpectMovieBean;
import com.cicinnus.cateye.tools.GlideManager;

import java.util.List;

/**
 * 电影 - 待映 - 近期最受期待
 * Created by Cicinnus on 2017/1/26.
 */
public class RecentExpectAdapter extends BaseQuickAdapter<ExpectMovieBean.DataBean.ComingBean, BaseViewHolder> {

   public RecentExpectAdapter(List<ExpectMovieBean.DataBean.ComingBean> data) {
      super(R.layout.item_wait_movie_recent_expect, data);
   }

   @Override
   protected void convert(final BaseViewHolder helper, final ExpectMovieBean.DataBean.ComingBean item) {
      String originUrl = item.getImg();
      String imgUrl = originUrl.replace("/w.h/", "/") + "@345w_480h_1e_1c_1l";
      GlideManager.loadImage(mContext, imgUrl, (ImageView) helper.getView(R.id.iv_recent_expect));
      helper.setText(R.id.tv_recent_expect_movie_name, item.getNm())
          .setText(R.id.tv_recent_expect_wish, String.format("%s人想看", item.getWish()))
          .setText(R.id.tv_recent_expect_time, item.getComingTitle().substring(0, item.getComingTitle().indexOf(" ")));
      helper.itemView.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
            MovieDetailActivity.start(mContext, item.getId());
         }
      });
   }
}
