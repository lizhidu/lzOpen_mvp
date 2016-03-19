package com.lizhidu.lzopen.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.lizhidu.lzopen.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by dulzh on 3/18/16.
 */
public class MoreActivity extends BaseActivity {
    @Bind(R.id.simple_tv_text)
    TextView simpleTvText;

    final String[] mManyWords = {"Hello", "I", "am", "your", "friend", "Spike"};
    final List<String> mManyWordList = Arrays.asList(mManyWords);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_simple;
    }

    // Action类似订阅者, 设置TextView
    private Action1<String> mTextViewAction = new Action1<String>() {
        @Override public void call(String s) {
            simpleTvText.setText(s);
        }
    };

    // Action设置Toast
    private Action1<String> mToastAction = new Action1<String>() {
        @Override public void call(String s) {
            Toast.makeText(MoreActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };

    // 设置映射函数
    private Func1<List<String>, Observable<String>> mOneLetterFunc = new Func1<List<String>, Observable<String>>() {
        @Override public Observable<String> call(List<String> strings) {
            return Observable.from(strings); // 映射字符串
        }
    };

    // 设置大写字母
    private Func1<String, String> mUpperLetterFunc = new Func1<String, String>() {
        @Override public String call(String s) {
            return s.toUpperCase(); // 大小字母
        }
    };

    // 连接字符串
    private Func2<String, String, String> mMergeStringFunc = new Func2<String, String, String>() {
        @Override public String call(String s, String s2) {
            return String.format("%s %s", s, s2); // 空格连接字符串
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 添加字符串, 省略Action的其他方法, 只使用一个onNext.
        Observable<String> obShow = Observable.just("hello world");

        // 先映射, 再设置TextView
        obShow.observeOn(AndroidSchedulers.mainThread())
                .map(mUpperLetterFunc).subscribe(mTextViewAction);

        // 单独显示数组中的每个元素
        Observable<String> obMap = Observable.from(mManyWords);

        // 映射之后分发
        obMap.observeOn(AndroidSchedulers.mainThread())
                .map(mUpperLetterFunc).subscribe(mToastAction);

        // 优化过的代码, 直接获取数组, 再分发, 再合并, 再显示toast, Toast顺次执行.
        Observable.just(mManyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(mOneLetterFunc)
                .reduce(mMergeStringFunc)
                .subscribe(mToastAction);



}

    @Override
    protected void requestData() {

    }
}
