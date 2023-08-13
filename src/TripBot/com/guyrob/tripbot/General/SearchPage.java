/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.General;
import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends base {



    public void selectProduct_ByName(String name){
        List<WebElement> hotels = driver.findElements(locate.SP_btn_productsList);
        for (WebElement ele : hotels){
            if (ele.getText().equals(name)){
                ele.click();
            }
        }
    }

    public void selectProduct_ByIndex(int index){
        List<WebElement> things = driver.findElements(locate.SP_btn_productsList);
        things.get(index-1).click();
    }

    public String getSearchText(){
        return driver.findElement(locate.SP_txt_searchResult).getText();
    }

}
