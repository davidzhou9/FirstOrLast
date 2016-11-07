package com.example.david.firstorlast;

/**
 * Created by david on 8/27/2016.
 */
import java.util.*;
import java.lang.*;

public class HistoricalEvent implements Comparable<HistoricalEvent>{

    private String eventName;
    private int year;

    public HistoricalEvent(int year, String eventName){
        this.year = year;
        this.eventName = createFormattedEvent(eventName);
    }

    public String createFormattedEvent(String name){
        String temp = "";
        int counter = 0;
        for (int i = 0; i<name.length(); i++){
            temp += name.charAt(i);
            if (counter>=20 && i<name.length()-1 && name.charAt(i+1)==' ') {
                temp += "\n";
                counter = 0;
            }
            counter++;
        }
        return temp;
    }

    public int getYear(){
        return this.year;
    }

    public String getEventName(){
        return this.eventName;
    }

    public int compareTo(HistoricalEvent otherEvent){
        return this.year-otherEvent.getYear();
    }

    public String toString(){
        return eventName+" (Occurred in "+year+")";
    }
}
