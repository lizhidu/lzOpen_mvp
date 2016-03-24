package com.lizhidu.lzopen.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewAttachEvent;
import com.lizhidu.lzopen.R;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by dulzh on 3/20/16.
 */
public class BindingActivity1 extends BaseActivity {
    @Bind(R.id.rx_button)
    Button rxButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding1;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxView.attachEvents(rxButton) // 以 Observable 形式来反馈点击事件
      .subscribe(new Action1<ViewAttachEvent>() {
          @Override
          public void call(ViewAttachEvent viewAttachEvent) {
              Toast.makeText(getApplicationContext(),"我被点击了",Toast.LENGTH_LONG).show();
          }
      });

    }

    @Override
    protected void requestData() {

    }
}
