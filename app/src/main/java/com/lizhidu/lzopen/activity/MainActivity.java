package com.lizhidu.lzopen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lizhidu.lzopen.R;
import com.lizhidu.lzopen.bean.NewsResult;
import com.lizhidu.lzopen.eventbus.Message;
import com.lizhidu.lzopen.rest.NetInterface;
import com.lizhidu.lzopen.rest.RestClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    @Bind(R.id.id_tv_json)
    TextView tv_json;
    @Bind(R.id.id_btn_button1)
    Button button;
    @Bind(R.id.id_tv_message)
    TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventbus,因为是twoactivity向Mainactivity发送消息，所以在此注册
        EventBus.getDefault().register(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        TwoActivity.class);
                startActivity(intent);
            }
        });
    }


    // 跳转简单的页面
    public void gotoSimpleModule(View view) {
        startActivity(new Intent(this, SimpleActivity.class));
    }

    // 跳转复杂的页面
    public void gotoMoreModule(View view) {
        startActivity(new Intent(this, MoreActivity.class));
    }

    // 跳转网络的页面
    public void gotoNetworkModule(View view) {
        startActivity(new Intent(this, NetworkActivity.class));
    }
/*


    // 跳转Lambda的页面
    public void gotoLambdaModule(View view) {
        startActivity(new Intent(this, LambdaActivity.class));
    }



    // 跳转线程安全的页面
    public void gotoSafeModule(View view) {
        startActivity(new Intent(this, SafeActivity.class));
    }

    // 跳转RxBinding的页面
    public void gotoBindingModule(View view) {
        startActivity(new Intent(this, BindingActivity.class));
    }
    */

    @Override
    protected void requestData() {
        NetInterface restClient = RestClient.getNetInterface();

//        Call<NewsResult> newsResultCall = restClient.getNews("9e1432111c23ce5c1a5cb8ebdc5a9c84", "两会", "");
//        newsResultCall.enqueue(new Callback<NewsResult>() {
//            @Override
//            public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
//                NewsResult result = response.body();
//                Log.d(MainActivity.class.getSimpleName(), "error_code=" + result.getError_code());
//                ArrayList<NewsResult.ResultEntity> mData = new ArrayList<NewsResult.ResultEntity>();
//                if (result != null) {
//                    for (NewsResult.ResultEntity bean : result.getResult()) {
//                        mData.add(bean);
//                    }
//                    Log.d(MainActivity.class.getSimpleName(), "mData.size=" + mData.size());
//                    tv_json.setText(result.toString());
//                } else {
//                    return;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsResult> call, Throwable t) {
//
//            }
//        });


        restClient.getNews("9e1432111c23ce5c1a5cb8ebdc5a9c84", "两会", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsResult>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getApplicationContext(), "---", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("================" + e);
                    }

                    @Override
                    public void onNext(NewsResult newsResult) {
                        Toast.makeText(getApplicationContext(), "---newsResult", Toast.LENGTH_LONG).show();
                        System.err.println("----------=" + newsResult.getError_code() + "---" + newsResult.getResult().size());
                        tv_json.setText(newsResult.toString());

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    // Called in Android UI's main thread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Message event) {
        tv_message.setText(event.getMsg());
        System.err.println("main---" + event.getMsg());
        Toast.makeText(getApplicationContext(), "" + event.getMsg(), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销eventbus
        EventBus.getDefault().unregister(this);
    }


}
