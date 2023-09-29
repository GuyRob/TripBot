import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.ThingProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class P2_topAttraction extends base {
    HomePage homepage;
    SearchPage searchPage;
    ThingProductPage thingProductPage;

    List<String> tabs;

    /** Test Data: */
    String destinationName = "Italy, Rome";
    String destinationOnly = "Rome";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();
        thingProductPage = new ThingProductPage();


        driver.manage().window().maximize();
        driver.get(testdata.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void P1_searchDestination(){
        homepage.SearchThing_SelectOption(destinationName, 1);
        allure_Log("Search " + destinationName);
        screenShot("Things\\P2", "P1_searchDestination");
        allure_LogAttachment("Selecting: " + destinationName, "Things\\P2", "P1_searchDestination");
        Assert.assertTrue(searchPage.getSearchText().contains(destinationOnly), "Wrong search destination!");
    }

    @Test
    public void P2_selectTopAttraction(){
        searchPage.TNG_topAttraction_ByIndex(1);
        screenShot("Things\\P2", "P2_selectTopAttraction");
        allure_LogAttachment("Selecting Attraction", "Things\\P2", "P2_selectTopAttraction");
        // todo add assert
    }

    @Test
    public void P3_visitWebsite(){
        String vendor = thingProductPage.visitWebsite();
        tabs = switchTab(2);
        screenShot("Things\\P2", "P3_visitWebsite");
        allure_LogAttachment("Visit website", "Things\\P2", "P3_visitWebsite");
        Assert.assertTrue(getCurrentURL(vendor), "Wrong thing selected!");
    }


}
