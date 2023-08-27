/*
 * (c) guyrob.tripbot
 */

package com.guyrob.tripbot.General;
import com.guyrob.tripbot.base;
import com.guyrob.tripbot.locate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SearchPage extends base {



    public void selectProduct_ByName(String name){
        waitVisibility(5, locate.SP_btn_productsList);
        List<WebElement> products = driver.findElements(locate.SP_btn_productsList);
        for (WebElement ele : products){
            if (ele.getText().equals(name)){
                ele.click();
            }
        }
    }

    public void selectProduct_ByIndex(int index){
        waitVisibility(5, locate.SP_btn_productsList);
        List<WebElement> products = driver.findElements(locate.SP_btn_productsList);
        products.get(index-1).click();
    }

    public void selectProduct_ByPrice() {
        int lowestPrice = 0;
        int lowestPriceIndex = 0;
        waitVisibility(5, locate.SP_btn_productsList);
        List<WebElement> products = driver.findElements(locate.SP_btn_productsList);
        List<WebElement> products_Prices = driver.findElements(locate.SP_txt_productsList_prices);

        for (int i = 0; i<products.size(); i++){
            String priceText = products_Prices.get(i).getAttribute("textContent");
            String numericOnlyText = priceText.replaceAll("[^0-9]", "");
            int priceInt = Integer.parseInt(numericOnlyText);

            if (priceInt > lowestPrice){
                lowestPrice = priceInt;
                lowestPriceIndex = i;
            }
        }

        System.out.println("index: " + lowestPriceIndex); // TODO DELETE
        System.out.println("price: " + lowestPrice); // TODO DELETE

        products.get(lowestPriceIndex).click();
    }

    public String getSearchText(){
        return driver.findElement(locate.SP_txt_searchResult).getText();
    }

    public WebElement filterEle_ByName (String name){
        return driver.findElement(By.xpath(locate.SP_xpath_filters+"//div[@class='CHHoy']//h3[contains(text(), '"+name+"')]"));
    }

    public void filterOpt_ByText(WebElement filterEle, String text){
        List <WebElement> options = filterEle.findElements(By.xpath("//span[@data-automation] | //span[normalize-space(text())]"));
        for (WebElement ele : options){
            if (ele.getText().equals(text)){
                ele.click();
                sleep(2000);
                break;
            }
        }
    }

    public void sort_ByText(String sortName) {
        waitVisibility(5, locate.SP_btn_sort);
        driver.findElement(locate.SP_btn_sort).click();
        List <WebElement> options = driver.findElements(locate.SP_btn_sortOptions);
        for (WebElement ele : options){
            if (ele.getText().equals(sortName)){
                ele.click();
                sleep(4000);
                break;
            }
        }
    }

    /** Hotels: */
    public boolean HOT_checkDates(String startDate, String endDate){
        try {
            String actualStartDate = driver.findElement(locate.SP_HOT_btn_checkIn).getText();
            String actualEndDate = driver.findElement(locate.SP_HOT_btn_checkOut).getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatActual = new SimpleDateFormat("MM/dd/yy");
            Date selectedStartDate = dateFormat.parse(startDate);
            Date selectedEndDate = dateFormat.parse(endDate);
            String formattedStartDate = dateFormatActual.format(selectedStartDate);
            String formattedEndDate = dateFormatActual.format(selectedEndDate);

            return actualStartDate.contains(""+formattedStartDate) && actualEndDate.contains(""+formattedEndDate);
        } catch (Exception e){
            Assert.fail("ERROR: Exception - " + e);
            return false;
        }
    }



}
