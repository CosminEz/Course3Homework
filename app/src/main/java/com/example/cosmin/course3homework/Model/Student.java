package com.example.cosmin.course3homework.Model;

import android.provider.ContactsContract;

import java.text.NumberFormat;

/**
 * Created by septy on 21.05.2017.
 */

public class Student{
    private String nume;
    private String prenume;
    private String facultate;
    private String telefon;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Student(){
        nume=null;
        prenume=null;
        facultate=null;
        telefon=null;
    }

    public Student(String nume,String prenume,String facultate,String telefon) {
        this.nume=nume;
        this.prenume=prenume;
        this.facultate=facultate;
        this.telefon=telefon;
    }
}
