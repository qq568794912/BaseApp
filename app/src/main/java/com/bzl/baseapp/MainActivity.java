package com.bzl.baseapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bzl.butterknife.ButterKnifeActivity;
import com.bzl.recyclerview.RecyclerViewActivity;
import com.bzl.rxjava.RxjavaActivity;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private static List<MainDataBean> mData = new ArrayList<>();

    static {
        mData.add(new MainDataBean("RecyclerView示例", RecyclerViewActivity.class));
        mData.add(new MainDataBean("RxJava示例", RxjavaActivity.class));
        mData.add(new MainDataBean("ButterKnife示例", ButterKnifeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.main_recyclerview);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initRecyclerView();

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MainAdapter(this, mData));
    }

}
