package com.openup.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Niel on 18/07/17.
 */

public abstract class BaseAdapter<T, VH extends BaseViewHolder<T>>
    extends RecyclerView.Adapter<VH> {

  private List<T> mData;
  private Context mContext;

  public BaseAdapter(Context context, List<T> data) {
    mContext = context;
    mData = data;
  }

  public BaseAdapter(){};

  @Override public final void onBindViewHolder(VH vh, int position) {
    T item = mData.get(position);
    vh.performBind(item, position);
  }

  @Override public int getItemCount() {
    if (mData == null) return 0;
    return mData.size();
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public List<T> getData() {
    return mData;
  }

  public void setData(List<T> data) {
    mData = data;
  }

  public void addData(List<T> data) {
    if (mData == null) {
      mData = data;
    } else {
      mData.addAll(data);
    }
  }

  public T getItem(int position) {
    return mData.get(position);
  }

  public void clear() {
    if (mData != null) mData.clear();
  }

  public Context getContext() {
    return mContext;
  }
}