package com.bzl.recyclerview;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

public class SRecyclerView extends RecyclerView {
  private static final String TAG = "SRecyclerView";

  // https://blog.csdn.net/fyfcauc/article/details/54342303

  // layout时，记录Recycler里mAttachedScrap
  // Recycler的第一层缓存
  // ScrapView：仍被Attach到RecyclerView上，有资格被重用或rebind。存在Recycler成员变量里
  public ArrayListWrapper<ViewHolder> mCachedRecord;

  // layout时，记录Recycler里mCachedViews
  // Recycler的第二层缓存，超长会移除后面放入Pool中（四级缓存）
  // size：setViewCacheSize(默认2)+Layout预拉取GapWorker个数(默认1)=3
  public ArrayListWrapper<ViewHolder> mAttachedRecord;


  public interface onLayoutListener {
    void beforeLayout();

    void afterLayout();
  }

  private onLayoutListener onLayoutListener;

  public void setOnLayoutListener(SRecyclerView.onLayoutListener onLayoutListener) {
    this.onLayoutListener = onLayoutListener;
  }

  public SRecyclerView(Context context) {
    this(context, null);
  }

  public SRecyclerView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mAttachedRecord = new ArrayListWrapper<>();
    mCachedRecord = new ArrayListWrapper<>();
    mAttachedRecord.setKey("mAttachedScrap");
    mCachedRecord.setKey("mCachedViews");
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    Log.d(TAG, "before onLayout");
    onLayoutListener.beforeLayout();
    super.onLayout(changed, l, t, r, b);
    onLayoutListener.afterLayout();
    Log.d(TAG, "after onLayout");
  }

  public void setAllCache() {
    try {
      Field mRecycler =
          Class.forName("android.support.v7.widget.RecyclerView").getDeclaredField("mRecycler");
      mRecycler.setAccessible(true);
      RecyclerView.Recycler recyclerInstance =
          (RecyclerView.Recycler) mRecycler.get(this);

      Class<?> recyclerClass = Class.forName(mRecycler.getType().getName());
      Field mAttachedScrap = recyclerClass.getDeclaredField("mAttachedScrap");
      mAttachedScrap.setAccessible(true);
      mAttachedScrap.set(recyclerInstance, mAttachedRecord);
      Field mCacheViews = recyclerClass.getDeclaredField("mCachedViews");
      mCacheViews.setAccessible(true);
      mCacheViews.set(recyclerInstance, mCachedRecord);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void onMeasure(int widthSpec, int heightSpec) {
    Log.d(TAG, "before onMeasure");
    super.onMeasure(widthSpec, heightSpec);
    Log.d(TAG, "after onMeasure");
  }

  @Override
  public void onDraw(Canvas c) {
    Log.d(TAG, "before onDraw");
    super.onDraw(c);
    Log.d(TAG, "after onDraw");
  }
}
