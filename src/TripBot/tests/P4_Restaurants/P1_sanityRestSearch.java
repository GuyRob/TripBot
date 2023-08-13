import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class P1_sanityRestSearch extends base {
    HomePage homepage;
    SearchPage searchPage;

    List<String> tabs;

    /** Test Data: */
    String restName = "Gulf Restaurant Eilat";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();

        driver.manage().window().maximize();
        driver.get("https://www.tripadvisor.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    @Test
    public void P1_searchRest(){
        homepage.searchRestaurant(restName);
        allure_Log("search");
        screenShot("Restaurants", "P1_searchRest", "search");
        Assert.assertTrue(restName.contains(searchPage.getSearchText()), "Wrong restaurants search!");
    }

}
