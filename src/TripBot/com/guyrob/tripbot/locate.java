/*
 * (c) guyrob.tripbot
 */
package com.guyrob.tripbot;

import org.openqa.selenium.By;

public class locate {
    /** HomePage */

    public static By HP_btn_SearchHotel = By.xpath("//*[@href='/Hotels' or text()='Hotels']");
    public static By HP_inp_SearchHotel = By.xpath("((//input[@role='searchbox'])[2]) | //input[@placeholder='Where to?'] | //input[contains(text(), 'Hotel')] | //input[@placeholder='Hotel name or destination']");

    public static By HP_btn_SearchThing = By.xpath("//*[@href='/Attractions' or text()='Things to Do']");
    public static By HP_inp_SearchThing = By.xpath("((//input[@role='searchbox']) | //input[@placeholder='Where to?'])[3] | //input[@placeholder='Attraction, activity or destination']");

    public static By HP_btn_SearchRestaurant = By.xpath("//*[@href='/Restaurants' or text()='Restaurants']");
    public static By HP_inp_SearchRestaurant = By.xpath("((//input[@role='searchbox']) | //input[@placeholder='Where to?'])[3] | //input[@placeholder='Restaurant or destination']");


    /** Search Page (General)*/
    public static By SP_txt_searchResult = By.xpath("//span[@class='title-query']");
    public static By SP_btn_productsList = By.xpath("//div[@class='result-title']/span");


    /** Product Page (General)*/
    // TODO Will be checked on thingProductPage

    /** HotelsProductPage */
    public static By HOTP_txt_searchResult = By.id("HEADING");

    // Dates (CheckIn)
    public static By HOTP_btn_checkIn = By.xpath("//div[@data-automation='checkin']");
    public static By HOTP_btn_checkOut = By.xpath("//div[@data-automation='checkout']");

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
    public static By HOTP_txt_agesList = By.xpath("//div[@class='QTOPt Z R2']//button/span");


    public static By childrenAges_Xpath(int index){
        return By.xpath("//button[@data-automation='child" + index + "']");
    }

    // Deals
    public static By HOTP_btn_Deals = By.xpath("//div[@id='DEALS']//a");


    /** ThingsPages */
    public static By TNGP_txt_searchResult = By.xpath("//h1[@data-automation='mainH1']");
    public static By TNGP_btn_visitWebsite = By.xpath("//div[@data-automation='WebPresentation_PoiOverviewWeb']//span[contains(text(), 'Visit website')]");


}
