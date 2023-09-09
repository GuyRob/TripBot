import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.General.SearchPage;
import com.guyrob.tripbot.ProductPages.HotelProductPage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.concurrent.TimeUnit;

public class P1_signIn extends base {
    HomePage homepage;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();

        driver.manage().window().maximize();
        driver.get(testdata.url);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void P1_signIn(){
        homepage.signIn_Email(testdata.userName, testdata.password);
    }

}
