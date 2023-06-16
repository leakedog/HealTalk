package com.example.firstprojecttry.Messenger;
import com.example.firstprojecttry.Logic.FeedContainer;
import com.example.firstprojecttry.Upload.uploadModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Messenger {
    public static FeedContainer<Chat> Chats = new FeedContainer<>();
    static FeedContainer<ArrayList<Integer>> chatMessages = new FeedContainer<>();

    public static FeedContainer<ArrayList<Integer>> userChat = new FeedContainer<>();

    static Map<Integer,Integer> lastMessageId = new HashMap<Integer , Integer >();
    static Integer getNextMessageId(Integer chatId)  {

        Chat chat = Chats.get(chatId);
        assert(chat != null);
        return chat.getMessages().size();
    }
    public static Chat startCommunication(Integer userA, Integer userB){
        if (userChat.get(userA) != null) {
            for (Integer chatid : userChat.get(userA)) {
                if (Chats.get(chatid).getA() == userB || Chats.get(chatid).getB() == userB) {
                    return Chats.get(chatid);
                }
            }
        }
        Chat created = new Chat(userA, userB);

        uploadModel.addChat(created);
        Chats.update(created.id, created);
        return created;
    }
    static Integer last_available = 0;
    static Integer getNextId(){
        while(Chats.get(last_available) != null){
            last_available++;
        }
        return last_available;
    }
    static public class Message{
        private Integer id;
        private String text;
        private String date;
        private Integer chat;
        private Integer sender;

        public Message(){
            this.id  = 0;
        }
        public Message(String text, String date, Integer chat, Integer sender, Integer id) {
            this.text = text;
            this.date = date;
            this.chat = chat;
            this.sender = sender;
            this.id = id;
        }
        public Message(String text, String date, Integer chat, Integer sender) {
            this.text = text;
            this.date = date;
            this.chat = chat;
            this.sender = sender;

            this.id = getNextMessageId(chat);

        }
        public Integer getId(){
            return id;
        }
        public void setId(Integer id){
            this.id = id;
        }
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getChat() {
            return chat;
        }

        public void setChat(Integer chat) {
            this.chat = chat;
        }

        public Integer getSender() {
            return sender;
        }

        public void setSender(Integer sender) {
            this.sender = sender;
        }
    }
    static public class ChatInfo{
        protected Integer id;
        protected Integer A,B;

        public ChatInfo(Integer id, Integer a, Integer b) {
            this.id = id;
            A = a;
            B = b;
        }
        public ChatInfo(Integer a, Integer b){
            A = a;
            B = b;
            this.id = getNextId();
        }
        public ChatInfo(){
            id = getNextId();
        }
        public Integer getId(){
            return id;
        }
        public void setId(Integer id){
            this.id = id;
        }

        public Integer getA() {
            return A;
        }

        public void setA(Integer a) {
            A = a;
        }

        public Integer getB() {
            return B;
        }

        public void setB(Integer b) {
            B = b;
        }
        public Integer getOpposite(Integer x) throws Exception{
            if(!x.equals(A) && !x.equals(B)) {
                throw new Exception("Tried to get Opposite of a user that is not in the chat");
            }
            return (x == A) ? B : A;
        }
    }
    static public class Chat extends ChatInfo{

        ArrayList<Message> messages;
        Chat(Integer id, Integer A, Integer B){
            super(id, A, B);
            messages = new ArrayList<Message>();
        }
        public Chat(){
            super();
            messages = new ArrayList<Message>();
        }
        public Chat(Integer A, Integer B){
            super(A, B);
            messages = new ArrayList<Message>();
        }

        public Integer getA(){
            return this.A;
        }
        public Integer getB(){
            return this.B;
        }
        public ArrayList<Message> getMessages(){
            return messages;
        }
        public void addMessage(Message msg){
            messages.add(msg);
        }
        public void setInfo(ChatInfo info){
            id = info.id;
            A = info.A;
            B = info.B;
        }
        public Message getLastMessage(){
            return (messages.isEmpty() ? null : messages.get(messages.size()-1));
        }
    }

    public static Chat cpy(Chat a){
        return new Chat(a.getId(), a.A, a.getB());
    }
}
