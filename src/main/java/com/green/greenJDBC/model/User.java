package com.green.greenJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private double distance;

    private Date date;

    private String name;

    private String text;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", distance=" + distance +
                ", run date='" + date + '\'' +
                ", username='" + name + '\'' +
                ", comment='" + text + '\'' +
                '}';
    }
}