package com.example.firstprojecttry;

import static com.example.firstprojecttry.Logic.User.container;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

interface CopyCat<T> {
     void copy(T o);
}



public class Logic {
    static private Integer lastUserId = 0;
    static public Integer getNextUserId(){
        while(User.container.get(lastUserId) != null)
            lastUserId++;
        return lastUserId;
    }
    public static class User{
        static FeedContainer<User> container = new FeedContainer<User>();
        protected String token;
        protected Photo photo;

        protected Description description;

        protected String name;

        protected int id;

        User() {
            token = new String();
            photo = new Photo();
            description = new Description();
            name = new String();
        }

        public void copy(User from){
            this.token = from.token;
            this.id = from.id;
        }
        public Photo getPhoto(){
            return this.photo;
        }
        public void setPhoto(Photo x){
            this.photo = x;
        }
        public String getToken(){
            return token;
        }
        public void setToken(){
            this.token = token;
        }
        public void setId(int id) {
            this.id = id;
        }
        public Integer getId() {
            return id;
        }

        public String getName(){return this.name;}
        public void setName(String s){this.name = s;}

        public Description getDescription() {
            return description;
        }
        public void setDescription(Description description) {this.description = description;}

    }
    public static class Location{
        private Double lat, lng;
        public Location() {
            lat = lng = 0.;
        }
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
    public static class Client extends User implements CopyCat<Client>{
        protected static FeedContainer<Client> container = new FeedContainer<Client>();

        private Side preferredSide;
        private Location location;
        private Rating rating;
        Client() {
            name = "";
            description = new Description();
            preferredSide = Side.BOTH;
            location = new Location();
            photo = new Photo();
            rating = calculate(this);
            id = getNextUserId();
            container.update(id, this);
        }

        Client(String Name, Description description, Photo photo, Side preferredSide, Location location, String token) {
            this.name = Name;
            this.description = description;
            this.preferredSide = preferredSide;
            this.location = location;
            this.rating = calculate(this);
            this.id = getNextUserId();
            this.token = token;
            this.photo = photo;
            container.update(this.id, this);
        }
        Client(String Name, Description description, Side preferredSide, Location location, String token) {
            this.name = Name;
            this.description = description;
            this.preferredSide = preferredSide;
            this.location = location;
            this.rating = calculate(this);
            this.id = getNextUserId();
            this.token = token;
            container.update(this.id, this);
        }
        @Override
        public void copy(Client change) {
            this.id = change.id;
            this.description = change.description;
            this.location = change.location;
            this.photo = change.photo;
            this.preferredSide = change.preferredSide;
            this.rating = change.rating;
        }

        public Photo getPhoto(){
            return this.photo;
        }
        public void setPhoto(Photo x){
            this.photo = x;
        }
        public String getToken(){
            return token;
        }
        public void setToken(){
            this.token = token;
        }
        public void setId(int id) {
            this.id = id;
        }
        public Integer getId() {
            return id;
        }

        public String getName(){return this.name;}
        public void setName(String s){this.name = s;}

        public Description getDescription() {
            return description;
        }
        public void setDescription(Description description) {this.description = description;}


        public Side getPreferredSide() {
            return preferredSide;
        }

        public Location getLocation() {
            return location;
        }


        public Rating getRating() {
            return rating;
        }


        public void setPreferredSide(Side preferredSide) {
            this.preferredSide = preferredSide;
        }

        public void setLocation(Location location) {
            this.location = location;
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
    public static class Executor extends User implements CopyCat<Executor> {
        public static FeedContainer<Executor> container = new FeedContainer<>();
        private Schedule schedule;
        private Integer price;
        private Rating rating;
        private Side preferredSide;
        private Location location;
        public Executor() {
            description = new Description();
            price = 0;
            preferredSide = Side.BOTH;
            photo = new Photo();
            rating = calculate(this);
            id = getNextUserId();
            container.update(id, this);
            System.out.println(this);
            schedule = new Schedule();
        }

        public Executor(Integer id, String name, Description description, Schedule schedule, Integer price, Photo photo, Side preferredSide, Location location, String token) {
            this.name = name;
            this.id = id;
            this.description = description;
            this.schedule = schedule;
            this.price = price;
            this.photo = photo;
            this.preferredSide = preferredSide;
            this.location = location;
            this.rating = calculate(this);
            this.token = token;
            container.update(this.id, this);
            System.out.println(description);
        }
        public Executor(Integer id, String name, Description description, Schedule schedule, Integer price, Photo photo, Side preferredSide, Location location) {
            this.name = name;
            this.id = id;
            this.description = description;
            this.schedule = schedule;
            this.price = price;
            this.photo = photo;
            this.preferredSide = preferredSide;
            this.location = location;
            this.rating = calculate(this);
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


        public Photo getPhoto(){
            return this.photo;
        }
        public void setPhoto(Photo x){
            this.photo = x;
        }
        public String getToken(){
            return token;
        }
        public void setToken(){
            this.token = token;
        }
        public void setId(int id) {
            this.id = id;
        }
        public Integer getId() {
            return id;
        }

        public String getName(){return this.name;}
        public void setName(String s){this.name = s;}

        public Description getDescription() {
            return description;
        }
        public void setDescription(Description description) {this.description = description;}
        public void setSchedule(Schedule schedule) {
            this.schedule = schedule;
        }

        public void setPrice(Integer price) {
            this.price = price;
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

        public Side getPreferredSide() {
            return preferredSide;
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

    }

    public static class Order implements CopyCat<Order> {
        static FeedContainer<Order> container = new FeedContainer<>();
        protected Integer id;
        private Client client;
        private Executor executor;
        private DateFormat date;
        private Integer customerExperience;
        private Integer executorExperience;
        //    private Integer hours;
        public Integer getId(){
            return this.id;
        }
        public void setId(Integer id){
            this.id = id;
        }
        Order(){}

        static public Integer getNextOrderId(){
            return container.getSize();
        }
        Order(Client client, Executor executor, DateFormat date, Integer customerExperience, Integer executorExperience/*, Integer hours*/){
            this.client = client;
            this.executor = executor;
            this.date = date;
            this.customerExperience = customerExperience;
            this.executorExperience = executorExperience;

            id = getNextOrderId();
            container.update(id, this);
        }

        public void copy(Order o) {
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
        public Map<String, Boolean> scheduleMap;
        public static List<String> keyNames;
        static {
            keyNames = new ArrayList<>();
            String []days = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
            String []times = {"Morning", "Afternoon", "Evening", "Night"};
            for (String time : times) {
                for (String day : days) {
                    keyNames.add(time + " + " + day);
                }
            }
        }
        Schedule() {

            scheduleMap = new HashMap<>();
            String []days = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
            String []times = {"Morning", "Afternoon", "Evening", "Night"};
            for (String time : times) {
                for (String day : days) {
                    scheduleMap.put(time + " + " + day, false);
                }
            }
            ids = "";
            prop = 3;
        }
    }
    public static class Description {
        public String aboutYou;
        public Long dateBirth;
        public Integer childNumber;
        public SexType sex;
        Description() {
            sex = SexType.MALE;
            childNumber = 0;
            aboutYou = new String("");
            dateBirth = 0L;
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
        Photo(String photoURL){
            this.photoURL = photoURL;
        }
        Photo(){}
    }

    enum SexType{
        MALE,
        FEMALE
    }

    public enum DescriptionType{
        STRING,
        NUMBER,
        DATE,
        CHECKBOX,
        PHOTO,
        SCHEDULE
    }
    public static class DescriptionCharacteristicField{
        public String title;
        public String body;
        public String textFieldTitle;
        public Integer restrictionValue;
        public Boolean shouldBeExtended;
        public Boolean changeable;

        public DescriptionType type;

        public static List<Class<?>> classToCheckList = List.of(Logic.Executor.class, Logic.User.class, Logic.Schedule.class, Logic.Description.class, Logic.Photo.class, Logic.Client.class);


        public DescriptionCharacteristicField(String title, String body, String textFieldTitle, DescriptionType type) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = 40;
            this.shouldBeExtended = false;
            this.changeable = true;
            this.type = type;
        }
        public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Boolean changeable, DescriptionType type) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = 40;
            this.shouldBeExtended = false;
            this.changeable = changeable;
            this.type = type;
        }
        public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Integer restrictionValue, DescriptionType type) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = restrictionValue;
            this.shouldBeExtended = (restrictionValue >= 100);
            this.changeable = true;
            this.type = type;
        }
        public DescriptionCharacteristicField(String title, String body, String textFieldTitle, Integer restrictionValue, Boolean changeable, DescriptionType type) {
            this.title = title;
            this.body = body;
            this.textFieldTitle = textFieldTitle;
            this.restrictionValue = restrictionValue;
            this.shouldBeExtended = (restrictionValue >= 100);
            this.changeable = changeable;
            this.type = type;
        }
        public static Object getObject(String name, Object user) {
            if (!user.getClass().isMemberClass() || user.getClass().isEnum())
                return null;
            Class<?> currentClass = user.getClass();

            // Get fields from the current class and its superclasses
            while (currentClass != null) {
                try {
                    var field = currentClass.getDeclaredField(name);
                    field.setAccessible(true);
                    return user;
                } catch (Exception error) {
                    for (Field field : currentClass.getDeclaredFields()) {
                        field.setAccessible(true);
                        try {
                            var result = getObject(name, Objects.requireNonNull(field.get(user)));
                            if (result != null) {
                                return result;
                            }
                        } catch (Exception e) {
                            //Ignored
                        }
                    }
                }
                currentClass = currentClass.getSuperclass();
            }

            return null;
        }
        public static Field getField(String name, Object user){
            if (!user.getClass().isMemberClass() || user.getClass().isEnum())
                return null;
            Class<?> currentClass = user.getClass();

            // Get fields from the current class and its superclasses
            while (currentClass != null) {
                try {
                    var field = currentClass.getDeclaredField(name);
                    field.setAccessible(true);
                    return field;
                } catch (Exception error) {
                    for (Field field : currentClass.getDeclaredFields()) {
                        field.setAccessible(true);
                        try {
                            var result = getField(name, Objects.requireNonNull(field.get(user)));
                            if (result != null) {
                                return result;
                            }
                        } catch (Exception e) {
                            //Ignored
                        }
                    }
                }
                currentClass = currentClass.getSuperclass();
            }

            return null;
        }
        public static Object getFieldValue(String name, User executor) {
            try {
                Object object = getObject(name, executor);
                Field field = getField(name, executor);
                return field.get(object);
            } catch (Exception e) {
                System.out.println("getFieldValue " + e.getMessage());
            }
            return null;
        }
    }


    public static Map<String, DescriptionCharacteristicField> descriptionMap = new HashMap<>();
    public static Map<String, Object> descriptionStates = new HashMap<>();

    public static List<String> descriptionNames = new ArrayList<>();
    public static List<String> clientDescriptionNames = new ArrayList<>();
    public static List<String> executorDescriptionNames = new ArrayList<>();


    static{
        descriptionMap.put("name",
                new DescriptionCharacteristicField("Tell us your legal name",
                                                        "Note that this name should match your document information",
                                                        "What's your name:", true, DescriptionType.STRING));
        descriptionNames.add("name");
        descriptionMap.put("aboutYou",
                new DescriptionCharacteristicField("About you",
                        "The information you share will be used across our service to help other users get to know you",
                        "Write about yourself:", 400, DescriptionType.STRING));
        descriptionNames.add("aboutYou");

        descriptionMap.put("dateBirth",
                new DescriptionCharacteristicField("Your date birth",
                        "You won't be able to change it after",
                        "Birth date:", 40,  false, DescriptionType.DATE));
        descriptionNames.add("dateBirth");

        descriptionMap.put("sex",
                new DescriptionCharacteristicField("Your sex",
                        "You won't be able to change it after",
                        "Sex:", 40,  false, DescriptionType.CHECKBOX));
        descriptionNames.add("sex");

        descriptionMap.put("childNumber",
                new DescriptionCharacteristicField("Do you have children?",
                        "The information you share will be used across our service to help others get to know you and make our service more efficient",
                        "Number of children:", 40,  true, DescriptionType.NUMBER));
        descriptionNames.add("childNumber");

        descriptionMap.put("photoURL",
                new DescriptionCharacteristicField("Choose your photo",
                        "By providing your photo, we can create a more personalized and engaging experience tailored specifically to you. Also that enables other users to identify you more easily. This fosters a sense of community and encourages meaningful interactions among our users.",
                        "Your photo:", 0, false, DescriptionType.PHOTO));
        descriptionNames.add("photoURL");

        descriptionMap.put("scheduleMap",
                    new DescriptionCharacteristicField("Choose your schedule",
                            "You should select your free time, so that we will be able to find client for you",
                            "Skip", true, DescriptionType.SCHEDULE));
        descriptionNames.add("scheduleMap");
        {
            Executor e = new Executor();
            Client c = new Client();

            System.out.println(Arrays.toString(c.getClass().getDeclaredFields()));
            for (String name : descriptionNames) {
                if (DescriptionCharacteristicField.getField(name, e) != null) {
                    executorDescriptionNames.add(name);
                }
                if (DescriptionCharacteristicField.getField(name, c) != null) {
                    clientDescriptionNames.add(name);
                }
            }

            System.out.println(clientDescriptionNames);
            System.out.println(descriptionNames);
            for (var x : descriptionMap.entrySet()) {
                try {
                    Field field = Logic.DescriptionCharacteristicField.getField(x.getKey(), e);
                    field.setAccessible(true);

                    System.out.println("UploadExecutor fieldName "  + x.getKey() +  ": " + field.getName()  + " " + Logic.DescriptionCharacteristicField.getObject(x.getKey(), e));
                }catch (Exception er) {
                    System.out.println("ERROR " + er.getMessage());
                }
            }
        }

    }

}