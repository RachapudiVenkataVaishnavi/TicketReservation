package com.example.homework_02;

import java.io.Serializable;
import java.util.Date;


public class Ticket implements Serializable {

    String name, deptTime, returnTime;

    /*
        Use CreateActivity.des[source/destination] to access "city, state" String for these fields
     */
    int source, destination;

    boolean roundTrip;
    String deptDate, returnDate;

    public Ticket(String name, String deptTime, String returnTime,
                  int source, int destination, boolean roundTrip, String deptDate, String returnDate) {
        this.name = name;
        this.deptTime = deptTime;
        this.returnTime = returnTime;
        this.source = source;
        this.destination = destination;
        this.roundTrip = roundTrip;
        this.deptDate = deptDate;
        this.returnDate = returnDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public boolean isRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    public String getDeptDate() {
        return deptDate;
    }

    public void setDeptDate(String deptDate) {
        this.deptDate = deptDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }


}
