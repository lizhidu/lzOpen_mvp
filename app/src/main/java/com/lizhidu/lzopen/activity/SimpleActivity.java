package com.lizhidu.lzopen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.AndroidCharacter;
import android.widget.TextView;

import com.lizhidu.lzopen.R;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Observers;
import rx.observers.Subscribers;

/**
 * Created by dulzh on 3/18/16.
 */
public class SimpleActivity extends BaseActivity {

    @Bind(R.id.simple_tv_text)
    TextView simpleTvText;

    private Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>(){

        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("我要下达指令了！");
            subscriber.onCompleted();
        }
    };

    //添加订阅者，修改控件文本
    private Subscriber<String> mTextSubscribers = new Subscriber<String>(){

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            simpleTvText.setText(s);

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建被观察者
        Observable<String> observable = Observable.create(mObservableAction);
        observable.observeOn(AndroidSchedulers.mainThread());
        //订阅者订阅被观察者
        observable.subscribe(mTextSubscribers);
        observable.subscribe();


    }

    @Override
    protected void requestData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_simple;
    }
}
