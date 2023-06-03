package com.example.firstprojecttry;


public class MessageModel {
    public static Messenger.Chat sendMessage(Messenger.Chat x, Messenger.Message msg) {
        x.addMessage(msg);
        uploadModel.addMessage(x.getId(), msg);
        return x;
    }
}
