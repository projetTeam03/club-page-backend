package com.projet.clubpage.entity;

import java.text.SimpleDateFormat;


public class Timestamp {
    public static void main(String[] args) {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        System.out.println(timestamp); // 생성한 timestamp 출력

        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(timestamp)); // format을 사용해 출력
    }
}