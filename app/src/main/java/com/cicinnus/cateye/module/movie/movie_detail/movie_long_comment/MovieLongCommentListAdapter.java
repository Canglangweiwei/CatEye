package com.cicinnus.cateye.module.movie.movie_detail.movie_long_comment;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cicinnus.cateye.R;
import com.cicinnus.cateye.base.BaseWebViewActivity;
import com.cicinnus.cateye.tools.GlideManager;
import com.cicinnus.cateye.tools.StringUtil;
import com.cicinnus.cateye.tools.TimeUtils;
import com.cicinnus.cateye.view.CircleImageView;
import com.cicinnus.cateye.view.RatingBar;

/**
 * 长评adapter
 */

public class MovieLongCommentListAdapter extends BaseQuickAdapter<MovieLongCommentBeanList.DataBean.FilmReviewsBean,BaseViewHolder> {
    public MovieLongCommentListAdapter() {
        super(R.layout.item_long_comment,null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MovieLongCommentBeanList.DataBean.FilmReviewsBean item) {
        helper.setText(R.id.tv_author_name,item.getAuthor().getNickName())
                .setText(R.id.tv_comment_title,item.getTitle())
                .setText(R.id.tv_comment_content,item.getText())
                .setText(R.id.tv_view_count,String.format("%s",item.getViewCount()))
                .setText(R.id.tv_comment_count,String.format("%s",item.getCommentCount()))
                .setText(R.id.tv_pub_time, TimeUtils.dateMD(item.getCreated()));


        RatingBar ratingBar = helper.getView(R.id.rb_score);
        ratingBar.setStar((float) item.getSc());

        Drawable icon = null;
        switch (item.getAuthor().getUserLevel()){
            case 1:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv1);
                break;
            case 2:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv2);
                break;
            case 3:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv3);
                break;
            case 4:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv4);
                break;
            case 5:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv5);
                break;
        }
        helper.setImageDrawable(R.id.iv_user_level,icon);
        GlideManager.loadImage(mContext,item.getAuthor().getAvatarurl(), (CircleImageView) helper.getView(R.id.civ_author));

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseWebViewActivity.start(helper.itemView.getContext(), StringUtil.getRealUrl(item.getUrl()));
            }
        });
    }
}
