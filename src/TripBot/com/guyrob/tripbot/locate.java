package com.guyrob.tripbot;

import org.openqa.selenium.By;

public class locate {
    /** HomePage */
    static By btn_SearchHotel = By.xpath("//a[@href='/Hotels']");
    static By txt_SearchHotel = By.xpath("//div[@data-test-attribute='typeahead-QuickLink_HOTELS_geopicker']//input[1]");

    /** HotelsPage */
    static By btn_HotelsTitile = By.xpath("//div[@class='location-meta-block']//div[@class='result-title']");

}
