package com.lizhidu.lzopen.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by dulzh on 3/15/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    //组件绑定view
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        requestData();
    }

    //请求网络，并处理获取的数据
    protected abstract void requestData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}



