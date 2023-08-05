package com.guyrob.tripbot;

import org.openqa.selenium.WebDriver;



public class base {
    public static WebDriver driver;
    public locate Locate;

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }


}
