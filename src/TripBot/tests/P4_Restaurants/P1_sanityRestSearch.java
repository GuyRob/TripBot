import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.RestaurantProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class P1_sanityRestSearch extends base {
    HomePage homepage;
    SearchPage searchPage;
    RestaurantProductPage restaurantProductPage;

    List<String> tabs;

    /** Test Data: */
    String restName = "Gulf Restaurant Eilat";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();
        restaurantProductPage = new RestaurantProductPage();

        driver.manage().window().maximize();
        driver.get(testdata.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void P1_searchRest(){
        homepage.searchRestaurant(restName);
        allure_Log("Search " + restName);
        Assert.assertTrue(restName.contains(searchPage.getSearchText()), "Wrong restaurants search!");
    }

    @Test
    public void P2_selectRest(){
        searchPage.selectProduct_ByIndex(1);
        tabs = switchTab(1);
        screenShot("Restaurants", "P2_selectRest");
        allure_LogAttachment("Selecting: " + restName, "Restaurants", "P2_selectRest");
        Assert.assertTrue(restName.contains(restaurantProductPage.getRestName()), "Wrong restaurants selected!");
    }

    @Test
    public void P3_visitWebsite(){
        String vendor = restaurantProductPage.visitWebsite();
        tabs = switchTab(2);
        sleep(1000);
        screenShot("Restaurants", "P3_visitWebsite");
        allure_LogAttachment("Visit website" , "Restaurants", "P3_visitWebsite");
        Assert.assertTrue(getCurrentURL(vendor), "Wrong Rest URL appears!");
    }

}
