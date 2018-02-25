package com.mrb.test.ooad;

/**
 * Created by Mr.B on 2018/2/25.
 */

public class MovieOnList {
    private String name;
    private String subTitle;
    private String time;
    private int classification;

    public MovieOnList(String name, String subTitle, String time, int classification) {
        this.name = name;
        this.subTitle = subTitle;
        this.time = time;
        this.classification = classification;
    }
    public int getClassification(){
        return classification;
    }
    public void setClassification(int classification){
        this.classification = classification;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSubTitle(){
        return subTitle;
    }
    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
}