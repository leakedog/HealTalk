package com.example.firstprojecttry;

import static androidx.compose.runtime.SnapshotStateKt.mutableStateOf;

import androidx.compose.runtime.MutableState;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;


abstract class CopyCat<T> {
    protected String token;
    public String getToken(){

        return token;
    }
    public void setToken(){
        this.token = token;
    }
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
    public static class Location{
        private Double lat, lng;

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Location(Double lat, Double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public Double getLng() {
            return lng;
        }
    }
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

        Client(String Name, Description description, Integer childrenNumber, Side preferredSide, String location, String token) {
            this.name = Name;
            this.description = description;
            this.childrenNumber = childrenNumber;
            this.preferredSide = preferredSide;
            this.location = location;
            this.rating = calculate(this);
            this.id = container.getSize();
            this.token = token;
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
        private Location location;
        Executor() {
            description = new Description();
            price = 0;
            preferredSide = Side.BOTH;
            photo = new Photo();
            rating = calculate(this);
            id = container.getSize();
            container.update(id, this);
            System.out.println(this);
            schedule = new Schedule();
        }

        Executor(Integer id, String name, Description description, Schedule schedule, Integer price, Photo photo, Side preferredSide, Location location) {
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
            System.out.println(description);
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

        public void setLocation(Location location) {
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

        public Location getLocation() {
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
        public Map<String, Boolean> mp;
        Schedule() {

            mp = new HashMap<>();
            String []days = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
            String []times = {"Morning", "Afternoon", "Evening", "Night"};
            for (String time : times) {
                for (String day : days) {
                    mp.put(time + " + " + day, false);
                }
            }
            ids = "";
            prop = 3;
        }
    }
    public static class Description {
        public String aboutYou;
        public Timestamp dateBirth;
        public Integer childNumber;
        public String sex;
        Description() {
            sex = new String("Male");
            childNumber = 0;
            aboutYou = new String("");
            dateBirth = new Timestamp(0);
        }

    }
    public static class Photo {
        public String photoURL;

        public String getPhotoURL() {
            return photoURL;
        }

        public void setPhotoURL(String photoURL) {
            this.photoURL = photoURL;
        }
    }


    public static class DescriptionCharacteristicField{
        public String title;
        public String body;
        public String textFieldTitle;
        public Integer restrictionValue;
        public Boolean shouldBeExtended;
        public Boolean changeable;

        public DescriptionCharacteristicField(String title, String body, String textFieldTitle) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = 40;
            this.shouldBeExtended = false;
        }
        public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Boolean changeable) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = 40;
            this.shouldBeExtended = false;
            this.changeable = changeable;
        }
        public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Integer restrictionValue) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = restrictionValue;
            this.shouldBeExtended = (restrictionValue >= 100);
        }
        public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Integer restrictionValue, Boolean changeable) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = restrictionValue;
            this.shouldBeExtended = (restrictionValue >= 100);
            this.changeable = changeable;
        }
        public static Object getObject(String name, Executor executor) {
            try {
                var field = Logic.Executor.class.getDeclaredField(name);
                field.setAccessible(true);
                return executor;
            } catch (Exception error) {
                try {
                    var field = Logic.Description.class.getDeclaredField(name);
                    field.setAccessible(true);
                    return executor.description;
                } catch (Exception error1) {
                    try {
                        var field = Logic.Photo.class.getDeclaredField(name);
                        field.setAccessible(true);
                        return executor.photo;
                    } catch (Exception error2) {
                        System.out.println("Exception in uploadExecutor: " + error2.getMessage());
                    }
                }
            }
            return null;
        }
        public static Field getField(String name, Executor executor) {
            try {
                var field = Logic.Executor.class.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (Exception error) {
                try {
                    var field = Logic.Description.class.getDeclaredField(name);
                    field.setAccessible(true);
                    return field;
                } catch (Exception error1) {
                    try {
                        var field = Logic.Photo.class.getDeclaredField(name);
                        field.setAccessible(true);
                        return field;
                    } catch (Exception error2) {
                        System.out.println("Exception in uploadExecutor: " + error2.getMessage());
                    }
                }
            }
            return null;
        }
        public static String getFieldValue(String name, Executor executor) {
            try {
                var field = Logic.Executor.class.getDeclaredField(name);
                field.setAccessible(true);
                return (String)field.get(executor);
            } catch (Exception error) {
                try {
                    var field = Logic.Description.class.getDeclaredField(name);
                    field.setAccessible(true);
                    return (String)field.get(executor.getDescription());
                } catch (Exception error1) {
                    try {
                        var field = Logic.Photo.class.getDeclaredField(name);
                        field.setAccessible(true);
                        return (String)field.get(executor.getPhoto());
                    } catch (Exception error2) {
                        System.out.println("Exception in uploadExecutor: " + error2.getMessage() + " " + name);
                    }
                }
            }
            return null;
        }
    }

    public static Map<String, DescriptionCharacteristicField> descriptionMap = new HashMap<>();
    public static Map<String, MutableState<String>> descriptionStates = new HashMap<>();

    static{
        descriptionMap.put("name",
                new DescriptionCharacteristicField("Tell us your legal name",
                                                        "Note that this name should match your document information",
                                                        "What's your name:", false));
        descriptionMap.put("aboutYou",
                new DescriptionCharacteristicField("About you",
                        "The information you share will be used across our service to help other users get to know you",
                        "Write about yourself:", 400));
        descriptionMap.put("dateBirth",
                new DescriptionCharacteristicField("Your date birth",
                        "You won't be able to change it after",
                        "Birth date:", 40,  false));
        descriptionMap.put("sex",
                new DescriptionCharacteristicField("Your sex",
                        "You won't be able to change it after",
                        "Sex:", 40,  false));
        descriptionMap.put("childNumber",
                new DescriptionCharacteristicField("Do you have children?",
                        "The information you share will be used across our service to help others get to know you and make our service more efficient",
                        "Number of children:", 40,  true));
        descriptionMap.put("photoURL",
                new DescriptionCharacteristicField("Choose your photo",
                        "By providing your photo, we can create a more personalized and engaging experience tailored specifically to you. Also that enables other users to identify you more easily. This fosters a sense of community and encourages meaningful interactions among our users.",
                        "Your photo:", 0, false));


    }

}