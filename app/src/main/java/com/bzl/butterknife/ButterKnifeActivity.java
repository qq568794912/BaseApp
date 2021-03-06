package com.bzl.butterknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.bzl.baseapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterKnifeActivity extends AppCompatActivity {

  @BindView(R.id.edit)
  EditText editText;
  @BindView(R.id.btn)
  Button button;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_butterknife);
    ButterKnife.bind(this);
  }
}
