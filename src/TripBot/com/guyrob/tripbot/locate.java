package com.guyrob.tripbot;

import org.openqa.selenium.By;

public class locate {
    /** HomePage */

    public static By HP_btn_SearchHotel = By.xpath("//*[@href='/Hotels' or text()='Hotels']");
    public static By HP_inp_SearchHotel = By.xpath("((//input[@role='searchbox'])[2]) | //input[@placeholder='Where to?'] | //input[contains(text(), 'Hotel')] | //input[@placeholder='Hotel name or destination']");

//    static By cpytxt_SearchHotel = By.xpath("((//input[@role='searchbox'])[2]) | //input[@placeholder='Where to?']");

    /** HotelsPage */
    public static By HOT_btn_HotelsTitile = By.xpath("//div[@class='result-title']/span");
    public static By HOT_txt_searchResult = By.xpath("(//span[@class='title-query'])[1]");

    /** HotelsProductPage */
    public static By HOTP_txt_searchResult = By.id("HEADING");
    public static By HOTP_btn_checkIn = By.xpath("//div[@data-automation='checkin']");
    // Dates (CheckIn)
    public static By HOTP_btn_datePicker = By.xpath("//div[@data-testid='day_picker']");
    public static By HOTP_btn_dateDays = By.xpath("//div[@role='row']//div[@role='gridcell']");
    public static By HOTP_txt_dateCurrentMonths_Year = By.xpath("//div[@role='heading']//div");

    public static By HOTP_btn_datePrev = By.xpath("//button[@data-testid='nav_prev']");
    public static By HOTP_btn_dateNext = By.xpath("//button[@data-testid='nav_next']");

    // Guests
    public static By HOTP_btn_guestsMenu = By.xpath("//div[@data-automation='guest']");
    public static By HOTP_inp_guestsUpdate = By.xpath("//div[@data-automation='guestsUpdateBtn']");

    public static By HOTP_txt_rooms = By.xpath("//span[@data-automation='roomsNum']");
    public static By HOTP_btn_rooms_prev = By.xpath("//button[@data-automation='roomsLess']");
    public static By HOTP_btn_rooms_next = By.xpath("//button[@data-automation='roomsMore']");


    public static By HOTP_txt_adults = By.xpath("//span[@data-automation='adultsNum']");
    public static By HOTP_btn_adults_prev = By.xpath("//button[@data-automation='adultsLess']");
    public static By HOTP_btn_adults_next = By.xpath("//button[@data-automation='adultsMore']");

    public static By HOTP_txt_childrens = By.xpath("//span[@data-automation='childrenNum']");
    public static By HOTP_btn_childrens_prev = By.xpath("//button[@data-automation='childrenLess']");
    public static By HOTP_btn_childrens_next = By.xpath("//button[@data-automation='childrenMore']");

    public static By childrenAges_Xpath(int index){
        return By.xpath("//button[@data-automation='child" + index + "']");
    }



}
