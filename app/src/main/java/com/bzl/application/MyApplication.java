package com.bzl.application;

import android.app.Application;
import android.support.annotation.Nullable;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.FontRequestEmojiCompatConfig;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.v4.provider.FontRequest;
import android.util.Log;

import com.bzl.baseapp.R;

public class MyApplication extends Application {

  private static final String TAG = "APPLICATION";

  @Override
  public void onCreate() {
    super.onCreate();
    final FontRequest fontRequest = new FontRequest(
        "com.google.android.gms.fonts",
        "com.google.android.gms",
        "Noto Color Emoji Compat",
        R.array.com_google_android_gms_fonts_certs);
    EmojiCompat.Config config = new FontRequestEmojiCompatConfig(this, fontRequest);
    config.setReplaceAll(true)
        .registerInitCallback(new EmojiCompat.InitCallback() {
          @Override
          public void onInitialized() {
            Log.i(TAG, "EmojiCompat initialized");
          }

          @Override
          public void onFailed(@Nullable Throwable throwable) {
            Log.e(TAG, "EmojiCompat initialization failed", throwable);
          }
        });
    // 下载需要google服务，只能用bundle形式
    BundledEmojiCompatConfig bundledEmojiCompatConfig = new BundledEmojiCompatConfig(this);
    EmojiCompat.init(bundledEmojiCompatConfig);
  }
}
