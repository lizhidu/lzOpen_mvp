package com.lizhidu.lzopen.eventbus;

/**
 * Created by dulzh on 3/15/16.
 */
public class Message {
    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
