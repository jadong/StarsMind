package com.dong.starsmind.tuling.entity;

import com.google.gson.Gson;

/**
 * Created by zengwendong on 16/11/24.
 */
public class MessageEntity {

    private String myMessage;

    public MessageEntity() {
    }

    public MessageEntity(String myMessage) {
        this.myMessage = myMessage;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
