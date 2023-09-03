import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.RentalProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.List;
import java.util.concurrent.TimeUnit;


// TODO not working - need to check
public class P1_sanityRentSearch extends base {
    HomePage homepage;
    SearchPage searchPage;
    RentalProductPage rentalProductPage;

    List<String> tabs;

    /** Test Data: */
    String vilaName = "Villa Riviera Eilat";


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();
        searchPage = new SearchPage();
        rentalProductPage = new RentalProductPage();

        driver.manage().window().maximize();
        driver.get(testdata.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    @Test
    public void P1_searchRental(){
        homepage.SearchRental(vilaName);
        allure_Log("Search " + vilaName);
        Assert.assertTrue(searchPage.getSearchText().contains(vilaName), "Wrong search rental!");
    }

    @Test
    public void P2_selectRental(){
        searchPage.selectProduct_ByIndex(1);
        tabs = switchTab(1);
        screenShot("Rentals", "P2_selectHotel");
        allure_LogAttachment("Selecting: " + vilaName, "Rentals", "P2_selectRental");
        Assert.assertTrue(rentalProductPage.getRentalName().contains(vilaName), "Wrong rental selected!");
    }

    @Test
    public void P3_selectDates(){
        rentalProductPage.setDates(testdata.sanity_StartDate, testdata.sanity_EndDate);
        allure_Log("Start date: " + testdata.sanity_StartDate + " End date: " + testdata.sanity_EndDate);
        Assert.assertTrue(rentalProductPage.checkDates(testdata.sanity_StartDate, testdata.sanity_EndDate));
    }

    @Test
    public void P4_selectGuests(){
        rentalProductPage.setGuests(7);
        screenShot("Rentals", "P4_selectGuests");
        allure_LogAttachment("Guests", "Rentals", "P4_selectGuests");
        rentalProductPage.updateGuests();

        // TODO add assert
    }



}
