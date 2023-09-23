import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.LoggedInPages;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.concurrent.TimeUnit;

public class P2_planTrip extends base {
    HomePage homepage;
    LoggedInPages loggedInPages;
    SearchPage searchPage;

    /** Test Data: */
    String tripName = "guyrob.tripbot.mexicocity.planTrip";
    String destinationName = "Mexico City";
    String sort_ByDistance = "Distance to city center";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        loggedInPages = new LoggedInPages();
        searchPage = new SearchPage();

        driver.manage().window().maximize();
        driver.get(testdata.url);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    @Test
    public void P1_signIn(){
        homepage.signIn_Email(testdata.userName, testdata.password);
        allure_Log("Login " + testdata.userName);
        Assert.assertTrue(homepage.checkSignIn());
    }

    @Test
    public void P2_tripsTab(){
        homepage.selectTripsTab();
        allure_Log("Trip tab");
        Assert.assertTrue(loggedInPages.isInTripTab());
    }

    @Test
    public void P3_createTrip(){
        loggedInPages.createTrip(tripName);
        sleep(4000);
        screenShot("Login\\P2", "P3_createTrip");
        allure_LogAttachment("Creating trip: " + tripName, "Login\\P2", "P3_createTrip");
        Assert.assertEquals(loggedInPages.getTrip(0).getText(), tripName);
    }

    @Test
    public void P4_searchHotel(){
        homepage.clickLogo();
        homepage.SearchHotel_SelectOption(destinationName, 1);
        allure_Log("Search " + destinationName);
        Assert.assertTrue(searchPage.getSearchText().contains(destinationName), "Wrong search hotel!");
    }

    @Test
    public void P5_filterHotelByDistance(){
        sleep(2000);
        searchPage.sort_ByText(sort_ByDistance);
        allure_Log("Sort by: " + sort_ByDistance);
        Assert.assertTrue(searchPage.checkSort(sort_ByDistance));
    }

    @Test
    public void P6_saveHotel(){
        searchPage.saveProduct_ByIndex(1);
        screenShot("Login\\P2", "P6_saveHotel");
        allure_LogAttachment("Sort + saving hotel", "Login\\P2", "P6_saveHotel");
    }



}
