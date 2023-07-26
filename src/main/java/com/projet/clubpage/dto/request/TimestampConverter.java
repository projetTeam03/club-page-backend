//package com.projet.clubpage.dto.request;
//
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class TimestampConverter {
//    public static void main(String[] args) {
//        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(currentTimestamp);
//
//        // Timestamp to String
//        String currentTimestampToString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTimestamp);
//        System.out.println(currentTimestampToString);
//
//        //  String to Timestamp
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        dateFormat.setLenient(false);// 날짜와 시간을 엄격하게 확인
//        try {
//            Date stringToDate = dateFormat.parse(currentTimestampToString);
//            Timestamp stringToTimestamp = new Timestamp(stringToDate.getTime());
//            System.out.println(stringToTimestamp);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//}