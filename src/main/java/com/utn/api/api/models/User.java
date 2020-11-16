package com.utn.api.api.models;


import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
public class User
{

    private long id;
    @Basic
    private String name;
    @Basic
    private String myBrowser;
    @Basic
    private String mySystem;
    @Basic
    private String fecha;

    public User(){}
    public User(String brow, String system, String format){
        this.myBrowser=brow;
        this.mySystem=system;
        this.fecha=format;
    }


    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Bienvenido usted es el "+this.name+" N° "+this.id+" ingresando con el navegador "+this.myBrowser
                +" y con el sistema "+this.mySystem+" en la fecha "+this.fecha;

    }

    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMyBrowser() {
        return myBrowser;
    }
    public void setMyBrowser(String myBrowser) {
        this.myBrowser = myBrowser;
    }
    public String getMySystem() {
        return mySystem;
    }
    public void setMySystem(String mySystem) {
        this.mySystem = mySystem;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    

}
