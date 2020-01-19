package com.bzl.baseapp;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bzl.util.ActivityUtils;

public class MainAdapter extends RecyclerView.Adapter {

  private List<MainDataBean> mData;
  private Context mContext;
  private View.OnClickListener mItemClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      if (v.getTag() instanceof Integer) {
        int position = (int) v.getTag();
        MainDataBean data = getItem(position);
        Intent i = new Intent(mContext, data.mClassName);
        ActivityUtils.startActivity(i);
      }
    }
  };

  public MainAdapter(Context context, List<MainDataBean> data) {
    mContext = context;
    mData = data;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View item = LayoutInflater.from(mContext).inflate(R.layout.main_list_item, null);
    MainViewHolder viewHolder = new MainViewHolder(item);
    viewHolder.itemView.setOnClickListener(mItemClickListener);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MainDataBean data = getItem(position);
    ((MainViewHolder) holder).mName.setText(data.mName);
    holder.itemView.setTag(position);
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }

  public MainDataBean getItem(int position) {
    return mData.get(position);
  }

  private static class MainViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;

    public MainViewHolder(View itemView) {
      super(itemView);
      mName = itemView.findViewById(R.id.main_list_item_name);
    }
  }
}
