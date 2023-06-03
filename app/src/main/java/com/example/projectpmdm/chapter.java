package com.example.projectpmdm;

public class chapter {

    String date;
    String url;
    String number;
    String idseries;

    public chapter(String date, String url,String number, String idseries) {
        this.date = date;
        this.url = url;
        this.number = number;
        this.idseries = idseries;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdseries() {
        return idseries;
    }

    public void setIdseries(String idseries) {
        this.idseries = idseries;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
