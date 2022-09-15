package com.example.server2.userEntity;

import java.lang.reflect.Field;

public class test {
    public static void main(String[] args) {
        for (Field f : User.class.getDeclaredFields()) {
            System.out.println(f);
        }
    }
}
