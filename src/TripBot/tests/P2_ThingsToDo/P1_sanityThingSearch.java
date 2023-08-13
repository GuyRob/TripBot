import com.guyrob.tripbot.HomePage.HomePage;
import com.guyrob.tripbot.HotelPages.HotelProductPage;
import com.guyrob.tripbot.HotelPages.SearchHotelPage;
import com.guyrob.tripbot.ThingsPages.SearchThingPage;
import com.guyrob.tripbot.ThingsPages.ThingProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class P1_sanityThingSearch extends base {
    HomePage homepage;
    SearchThingPage searchThingPage;
    ThingProductPage thingProductPage;

    /** Test Data: */
    String thingName = "Dolphin Eilat";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchThingPage = new SearchThingPage();


        driver.manage().window().maximize();
        driver.get("https://www.tripadvisor.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void P1_searchThing(){
        homepage.searchThing(thingName);
        allure_Log("search");
        screenShot("Things", "P1_searchThing", "search");
        Assert.assertTrue(thingName.contains(searchThingPage.getSearchText()), "Wrong search thing!");
    }


}
