package com.example.laptop.petshappy.ui.fujin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.petshappy.R;
import com.example.laptop.petshappy.ui.fujin.adapter.bean.FuJinBean;

import java.util.List;

/**
 * Created by Laptop on 2018/1/6.
 */
public class FuJinAdapter extends RecyclerView.Adapter<FuJinAdapter.HolderOne>{
    private List<FuJinBean.DescBean> mList;
    private Context mContext;

    public FuJinAdapter(List<FuJinBean.DescBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public HolderOne onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        HolderOne holderOne = new HolderOne(view);
        return holderOne;
    }

    @Override
    public void onBindViewHolder(final HolderOne holder, int position) {
        final FuJinBean.DescBean descBean = mList.get(position);
        holder.mName.setText(descBean.getFamily());
        holder.mName1.setText(descBean.getAddress());
        holder.mName2.setText("¥"+descBean.getPrice()+"起");
        holder.mName3.setText("距"+descBean.getDistance()+"km");
        String userImage = descBean.getUserImage();
        Glide.with(mContext).load(userImage).into(holder.mImage);
        holder.ratingBar.setRating((float) descBean.getScore());
    }

    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    class HolderOne extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mName, mName1, mName2, mName3;
        private RatingBar ratingBar;

        public HolderOne(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.imageView3);
            mName = (TextView) itemView.findViewById(R.id.textView8);
            mName1 = (TextView) itemView.findViewById(R.id.textView7);
            mName2 = (TextView) itemView.findViewById(R.id.textView10);
            mName3 = (TextView) itemView.findViewById(R.id.textView9);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rtbProductRating);
        }
    }
}
