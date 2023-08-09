package com.guyrob.tripbot.HotelPages;
import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchHotelPage extends base {



    public void selectHotel_ByName(String name){
        List<WebElement> hotels = driver.findElements(locate.HOT_btn_HotelsTitile);
        for (WebElement ele : hotels){
            if (ele.getText().equals(name)){
                ele.click();
            }
        }
    }

    public String getSearchText(){
        return driver.findElement(locate.HOT_txt_searchResult).getText();
    }

}
