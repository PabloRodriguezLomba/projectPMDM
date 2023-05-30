package com.example.projectpmdm;

import java.sql.Blob;

public class novel {

    String nombre;
    Integer Image;
    String Type;
    String Genre;
    String Author;
    Integer Rating;
    String Lenguage;

    public novel(String nombre, Integer image, String type, String genre, String author,String lenguage,Integer rating) {
        this.nombre = nombre;
        Image = image;
        Type = type;
        Genre = genre;
        Author = author;
        Rating = rating;
        Lenguage = lenguage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getImage() {
        return Image;
    }

    public void setImage(Integer image) {
        Image = image;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getLenguage() {
        return Lenguage;
    }

    public void setLenguage(String lenguage) {
        Lenguage = lenguage;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }
}
