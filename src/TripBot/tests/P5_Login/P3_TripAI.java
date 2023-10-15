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

public class P3_TripAI extends base {
    HomePage homepage;
    LoggedInPages loggedInPages;
    SearchPage searchPage;

    /** Test Data: */
    String tripName = "guyrob.tripbot.mexicocity.TripAI";
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
    public void P3_createTripAI(){
//        loggedInPages.createTripAI();
        sleep(4000);
//        screenShot("Login\\P2", "P3_createTrip");
//        allure_LogAttachment("Creating trip: " + tripName, "Login\\P2", "P3_createTrip");
//        Assert.assertEquals(loggedInPages.getTrip(0).getText(), tripName);
    }

}
