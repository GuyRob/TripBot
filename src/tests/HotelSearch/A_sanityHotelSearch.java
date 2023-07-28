import HomePage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class A_sanityHotelSearch {
    WebDriver driver;
    HomePage homepage;
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage(driver);
        driver.get("https://www.tripadvisor.com/");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void searchHotel(){
        homepage.SearchHotel("Leonardo Privilege Hotel Eilat");
    }

    }
