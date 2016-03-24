package com.lizhidu.lzopen.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.lizhidu.lzopen.R;

import butterknife.Bind;

/**
 * Created by dulzh on 3/18/16.
 */
public class NetworkActivity extends BaseActivity {
    @Bind(R.id.network_rv_list)
    RecyclerView networkRvList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_network;
    }

    @Override
    protected void requestData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
