import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.ThingProductPage;
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
    SearchPage searchPage;
    ThingProductPage thingProductPage;

    List<String> tabs;

    /** Test Data: */
    String thingAndLocationName = "Dolphin Eilat";
    String selectedThing = "Dolphin Reef";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();
        thingProductPage = new ThingProductPage();


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
        homepage.searchThing(thingAndLocationName);
        allure_Log("Search " + thingAndLocationName);
        Assert.assertTrue(thingAndLocationName.contains(searchPage.getSearchText()), "Wrong search thing!");
    }

    @Test
    public void P2_selectThing(){
        searchPage.selectProduct_ByIndex(1);
        tabs = switchTab(1);
        screenShot("Things", "P2_selectThing");
        allure_LogAttachment("Selecting: " + thingAndLocationName, "Things", "P2_selectThing");
        Assert.assertTrue(selectedThing.contains(thingProductPage.getThingName()), "Wrong thing selected!");
    }

    @Test
    public void P3_visitWebsite(){
        String vendor = thingProductPage.visitWebsite();
        tabs = switchTab(2);
        screenShot("Things", "P3_visitWebsite");
        allure_LogAttachment("Visit website", "Things", "P3_visitWebsite");
        Assert.assertTrue(getCurrentURL(vendor), "Wrong thing selected!");
    }


}
