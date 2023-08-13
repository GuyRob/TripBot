package com.guyrob.tripbot.ThingsPages;

import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;

public class SearchThingPage extends base {

    public String getSearchText(){
        return driver.findElement(locate.SP_txt_searchResult).getText();
    }

}
