package com.guyrob.tripbot;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage extends base{

//    Locate locate = new locate();


    public void SearchHotel(String hotel) {
            driver.findElement(locate.btn_SearchHotel).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            driver.findElement(locate.txt_SearchHotel).sendKeys(hotel + Keys.ENTER);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

}
