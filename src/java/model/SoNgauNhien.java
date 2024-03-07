/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Base64;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author User
 */
public class SoNgauNhien {

    public String maOTP() {
        Random rd = new Random();
        String r1 = rd.nextInt(10) + "";
        String r2 = rd.nextInt(10) + "";
        String r3 = rd.nextInt(10) + "";
        String r4 = rd.nextInt(10) + "";
        String r5 = rd.nextInt(10) + "";
        String r6 = rd.nextInt(10) + "";
        String origin = r1 + r2 + r3 + r4 + r5 + r6;
        //String encodedString = Base64.getEncoder().encodeToString(origin.getBytes());
        //System.out.println(encodedString.substring(0, 6));
//        byte[] decodedBytes = Base64.getDecoder().decode(origin);
//        System.out.println(decodedBytes);
        return origin;
    }

    public static void main(String[] args) {
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.SECOND, 30);
//        LocalTime local = LocalTime.now();
//        int second = local.getSecond();
        //System.out.println(second);
//        System.out.println(maOTP());
//        int thoigianhieuluc = local.getSecond() + 60;
//        System.out.println(local);
//        System.out.println(thoigianhieuluc);
//        Date today = new Date(new java.util.Date().getTime());
//        Calendar lich = Calendar.getInstance();
//        lich.setTime(today);
//        lich.add(Calendar.SECOND, 60);
//        today = lich.getTime();
//        System.out.println(today);
        SoNgauNhien a = new SoNgauNhien();
        System.out.println(a.maOTP());
    }

}
