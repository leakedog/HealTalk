package com.example.firstprojecttry;

import java.text.DateFormat;
import java.util.*;


abstract class CopyCat<T> {

    protected int id;
    abstract void copy(T o);
    public void setId(int id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}

class FeedContainer <T extends CopyCat<T>> {
    Map<Integer, T> container;
    FeedContainer() {
        container = new HashMap<>();
    }
    void update(int id, T value) {
        System.out.println("Copied successfully!");
        if (container.containsKey(id)) {
            Objects.requireNonNull(container.get(id)).copy(value);
        } else {
            container.put(id, value);
        }
    }
    Integer getSize() {
        return container.size();
    }
    T get(Integer i) {
        return container.get(i);
    }
}

public class Logic {

    public static class Client extends CopyCat<Client>{
        protected static FeedContainer<Client> container = new FeedContainer<>();
        private String name;
        private Description description;
        private Integer childrenNumber; /// how many need to be looked after
        private Side preferredSide;
        private String location;
        private Photo photo;
        private Rating rating;
        Client() {
            name = "";
            description = new Description();
            childrenNumber = 1;
            preferredSide = Side.BOTH;
            location = "";
            photo = new Photo();
            rating = calculate(this);
            id = container.getSize();
            container.update(id, this);
        }

        Client(String Name, Description description, Integer childrenNumber, Side preferredSide, String location) {
            this.name = Name;
            this.description = description;
            this.childrenNumber = childrenNumber;
            this.preferredSide = preferredSide;
            this.location = location;
            this.rating = calculate(this);
            this.id = container.getSize();
            container.update(this.id, this);
        }

        public void copy(Client change) {
            this.id = change.id;
            this.childrenNumber = change.childrenNumber;
            this.description = change.description;
            this.location = change.location;
            this.photo = change.photo;
            this.preferredSide = change.preferredSide;
            this.rating = change.rating;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }


        public Description getDescription() {
            return description;
        }

        public Integer getChildrenNumber() {
            return childrenNumber;
        }

        public Side getPreferredSide() {
            return preferredSide;
        }

        public String getLocation() {
            return location;
        }

        public Photo getPhoto() {
            return photo;
        }

        public Rating getRating() {
            return rating;
        }


        public void setDescription(Description description) {
            this.description = description;
        }

        public void setChildrenNumber(Integer childrenNumber) {
            this.childrenNumber = childrenNumber;
        }

        public void setPreferredSide(Side preferredSide) {
            this.preferredSide = preferredSide;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

    }
    public static enum Side{
        CLIENTHOME,
        EXECUTORHOME,
        BOTH
    }
    public static class Executor extends CopyCat<Executor> {
        protected static FeedContainer<Executor> container = new FeedContainer<>();
        private String name;
        private Description description;
        private Schedule schedule;
        private Integer price;
        private Photo photo;
        private Rating rating;
        private Side preferredSide;
        private String location;
        Executor() {
            description = new Description();
            price = 0;
            preferredSide = Side.BOTH;
            location = "";
            photo = new Photo();
            rating = calculate(this);
            id = container.getSize();
            container.update(id, this);
            System.out.println(this);
            schedule = new Schedule();
        }

        Executor(Integer id, String name, Description description, Schedule schedule, Integer price, Photo photo, Side preferredSide, String location) {
            this.name = name;
            this.id = id;
            this.description = description;
            this.schedule = schedule;
            this.price = price;
            this.photo = photo;
            this.preferredSide = preferredSide;
            this.location = location;
            this.rating = calculate(this);
            this.id = container.getSize();
            container.update(this.id, this);
        }

        @Override
        public void copy(Executor o) {
            this.id = o.id;
            this.name = o.name;
            this.description = o.description;
            this.schedule = o.schedule;
            this.photo = o.photo;
            this.price = o.price;
            this.preferredSide = o.preferredSide;
            this.location = o.location;
        }

        public void setName(String name) {
            name = name;
        }


        public void setDescription(Description description) {
            this.description = description;
        }

        public void setSchedule(Schedule schedule) {
            this.schedule = schedule;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public void setPreferredSide(Side preferredSide) {
            this.preferredSide = preferredSide;
        }

        public void setLocation(String location) {
            this.location = location;
        }
        public String getName() {return name; }

        public Side getPreferredSide() {
            return preferredSide;
        }

        public Photo getPhoto() {
            return photo;
        }

        public Rating getRating() {
            return rating;
        }

        public Integer getPrice() {
            return price;
        }

        public Schedule getSchedule() {
            return schedule;
        }

        public String getLocation() {
            return location;
        }

        public Description getDescription() {
            return description;
        }

    }

    public static class Order extends CopyCat<Order> {
        static FeedContainer<Order> container = new FeedContainer<>();

        private Client client;
        private Executor executor;
        private DateFormat date;
        private Integer customerExperience;
        private Integer executorExperience;
        //    private Integer hours;
        Order(){}


        Order(Client client, Executor executor, DateFormat date, Integer customerExperience, Integer executorExperience/*, Integer hours*/){
            this.client = client;
            this.executor = executor;
            this.date = date;
            this.customerExperience = customerExperience;
            this.executorExperience = executorExperience;
            id = container.getSize();
            container.update(id, this);
        }

        void copy(Order o) {
            this.client = o.client;
            this.executor = o.executor;
            this.date = o.date;
            this.customerExperience = o.customerExperience;
            this.executorExperience = o.executorExperience;
        }

        public Client getClient(){
            return client;
        }
        public Executor getExecutor(){
            return executor;
        }
        public DateFormat getDate(){
            return date;
        }
        public int getCustomerExperience(){
            return customerExperience;
        }
        public int getExecutorExperience(){
            return executorExperience;
        }


        public void setExecutorExperience(Integer executorExperience) {
            this.executorExperience = executorExperience;
        }

        public void setCustomerExperience(Integer customerExperience) {
            this.customerExperience = customerExperience;
        }

        public void setDate(DateFormat date) {
            this.date = date;
        }

        public void setExecutor(Executor executor) {
            this.executor = executor;
        }

        public void setClient(Client client) {
            this.client = client;
        }




    }

    public static class Rating {
        @Override
        public String toString() {
            return "Rating{" +
                    "grade=" + grade +
                    ", numberOfOrders=" + numberOfOrders +
                    '}';
        }

        private double grade;
        private Integer numberOfOrders;
        Rating() {
            grade = 0;
            numberOfOrders = 0;
        }

        public double getGrade(){
            return grade;
        }
        public int getNumberOfOrders(){
            return numberOfOrders;
        }
        public void setGrade(double value){
            grade = value;
        }
        public void setNumberOfOrders(int cnt){
            numberOfOrders = cnt;
        }

    }
    static Rating calculate(Object element){

        try {
            int sum = 0;
            int cnt = 0;
            for (int i = 0; i < Order.container.getSize(); ++i) {
                if (element == Order.container.get(i).getClient()) {
                    sum += Order.container.get(i).getCustomerExperience();
                    cnt++;
                }
                if (element == Order.container.get(i).getExecutor()) {
                    sum += Order.container.get(i).getExecutorExperience();
                    cnt++;
                }
            }
            Rating answer = new Rating();
            answer.setGrade((cnt == 0 ? 0 : (double) sum / (double) cnt));
            answer.setNumberOfOrders(cnt);
            return answer;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Rating();
        }
    }


    public static class Schedule {
        public Integer prop;
        public String ids;
        public Map<Integer, Integer> mp;
        Schedule() {
            mp = new HashMap<>();
            ids = "";
            prop = 3;
        }
    }
    public static class Description {
        private String text;

        public Description(String text) {
            this.text = text;
        }

        Description() {
            this.text = "";
        }

        @Override
        public String toString() {
            return "Description{" +
                    "text='" + text + '\'' +
                    '}';
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    public static class Photo {
        public Integer id;
    }

}