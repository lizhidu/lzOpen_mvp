package com.lizhidu.lzopen.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lizhidu.lzopen.R;
import com.lizhidu.lzopen.eventbus.Message;

import org.greenrobot.eventbus.EventBus;

public class TwoActivity extends BaseActivity {
   private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button2 = (Button) findViewById(R.id.id_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Message("你好，我是TwoActivity！"));
                System.err.println("Two---"+new Message("你好，我是TwoActivity！").getMsg());
                finish();

            }
        });
    }

    @Override
    protected void requestData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_two;
    }
}
