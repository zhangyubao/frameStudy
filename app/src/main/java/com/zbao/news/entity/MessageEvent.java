package com.zbao.news.entity;

/**
 * EventBus测试实体
 * Created by zhangYB on 2016/5/11.
 */
public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
