package com.bzl.baseapp;

public class MainDataBean {

  public String mName; // 显示名字
  public Class mClassName; // 跳转activity

  public MainDataBean(String mName, Class mClassName) {
    this.mName = mName;
    this.mClassName = mClassName;
  }
}
