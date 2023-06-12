package com.example.firstprojecttry.Logic;


public class Description {
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