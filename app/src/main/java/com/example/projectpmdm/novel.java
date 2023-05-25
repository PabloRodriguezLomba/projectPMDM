package com.example.projectpmdm;

import java.sql.Blob;

public class novel {

    String nombre;
    Blob Image;
    String Type;
    String Genre;
    String Author;

    String Lenguage;

    public novel(String nombre, Blob image, String type, String genre, String author,String lenguage) {
        this.nombre = nombre;
        Image = image;
        Type = type;
        Genre = genre;
        Author = author;
        Lenguage = lenguage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Blob getImage() {
        return Image;
    }

    public void setImage(Blob image) {
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
}
