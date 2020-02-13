package com.bzl.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.bzl.baseapp.R;
import com.bzl.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmojiActivity extends AppCompatActivity {

  @BindView(R.id.emoji_edit)
  EditText editText;
  @BindView(R.id.emoji_toast)
  Button button;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emoji);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.emoji_toast)
  void toastEmoji() {
    ToastUtils.showLong(editText.getText());
  }
}
