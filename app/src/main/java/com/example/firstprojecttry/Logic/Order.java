package com.example.firstprojecttry.Logic;

import com.example.firstprojecttry.FeedContainer;

import java.text.DateFormat;

public class Order implements CopyCat<Order> {
    public static FeedContainer<Order> container = new FeedContainer<>();
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
