package com.guyrob.tripbot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchHotelPage {

    WebDriver driver;

    public SearchHotelPage(WebDriver driver){
        this.driver = driver;
    }

    public void selectHotel_ByName(String name){
        List<WebElement> hotels = driver.findElements(locate.btn_HotelsTitile);
        for (WebElement ele : hotels){
            if (ele.findElement(By.xpath("/span")).getText().equals(name)){
                ele.click();
            }
        }
    }

}
