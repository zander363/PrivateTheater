package com.mrb.test.ooad;

/**
 * Created by Mr.B on 2018/3/1.
 */

public class MovieInDetail extends MovieOnList {
    String date;
    String type;
    String director;
    String actor;
    String decris;
    double score;
    MovieInDetail(){
        super();
        date="2018-07-15";
        type="喜劇";
        director="李奧納多皮卡丘三小";
        actor="你媽超胖";
        decris="影片簡介:\\n嫌犯呢為甚麼呢之所以呢\\n這個逃之夭夭\\n那位甚麼呢肇事夭夭呢\\n因為呢所以呢逮捕到案";
        score=8.7;
    }

    MovieInDetail(String name,String subTitle,String time,int classification,
                  String date,String type,String director,String actor,String decris,double score){
        super(name,subTitle,time,classification);
        this.date=date;
        this.type=type;
        this.director=director;
        this.actor=actor;
        this.decris=decris;
        this.score=score;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String  date){
        this.date=date;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getDirector(){
        return director;
    }
    public void setDirector(String director){
        this.director=director;
    }
    public String getActor(){
        return actor;
    }
    public void setActor(String actor){
        this.actor=actor;
    }
    public String getDecris(){
        return decris;
    }
    public void setDecris(String decris){this.decris=decris;}
    public double getScore(){return score;}
    public void setScore(double score){this.score=score;}
}
