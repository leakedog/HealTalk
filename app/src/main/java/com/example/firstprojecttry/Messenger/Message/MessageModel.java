package com.example.firstprojecttry.Messenger.Message;


import com.example.firstprojecttry.Messenger.Messenger;
import com.example.firstprojecttry.Upload.uploadModel;

public class MessageModel {
    public static Messenger.Chat sendMessage(Messenger.Chat x, Messenger.Message msg) {
        x.addMessage(msg);
        uploadModel.addMessage(x.getId(), msg);
        return x;
    }
}
