package com.ysl.livestockmonitor.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ysl.livestockmonitor.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

	private Context mContext;

	private List<ArticleItem> mArticleList;

	static class ViewHolder extends RecyclerView.ViewHolder{
		CardView cardView;
		ImageView imageView;
		TextView tv_synopsis,tv_date;

		public ViewHolder(View itemView){
			super(itemView);
			cardView = (CardView)itemView;
			imageView = (ImageView)itemView.findViewById(R.id.iv_article_preview);
			tv_date = (TextView)itemView.findViewById(R.id.tv_article_date);
			tv_synopsis = (TextView)itemView.findViewById(R.id.tv_article_synopsis);
		}
	}

	public ArticleAdapter(List<ArticleItem> articleItemList){
		mArticleList = articleItemList;
	}
	@NonNull
	@Override
	public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		if (mContext == null){
			mContext = viewGroup.getContext();
		}
		View view = LayoutInflater.from(mContext).inflate(R.layout.article_view_item,viewGroup,false);
		final ViewHolder holder = new ViewHolder(view);
		//以下省略点击事件
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder viewHolder, int i) {
		ArticleItem articleItem = mArticleList.get(i);
		viewHolder.tv_synopsis.setText(articleItem.getSynopsis());
		viewHolder.tv_date.setText(articleItem.getDate());
		Glide.with(mContext).load(articleItem.getImgID()).into(viewHolder.imageView);
	}

	@Override
	public int getItemCount() {
		return mArticleList.size();
	}
}
