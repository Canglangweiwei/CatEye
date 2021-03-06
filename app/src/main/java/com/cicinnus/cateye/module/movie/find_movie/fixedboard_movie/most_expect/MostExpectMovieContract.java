package com.cicinnus.cateye.module.movie.find_movie.fixedboard_movie.most_expect;

import com.cicinnus.retrofitlib.base.ICoreLoadingView;

/**
 * Created by Administrator on 2017/2/4.
 */

public class MostExpectMovieContract {
   public interface IRecentExpectMovieView extends ICoreLoadingView {
      void addRecentExpectMovie(MostExpectMovieBean.DataBean movies);

      void addData(String content, String date);
   }

   public interface IRecentExpectMoviePresenter {

      void getRecentExpectMovie(int offset);
   }
}
