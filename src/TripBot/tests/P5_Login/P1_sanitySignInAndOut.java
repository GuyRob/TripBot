import com.guyrob.tripbot.General.HomePage;
import com.guyrob.tripbot.base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.testdata;

import java.util.concurrent.TimeUnit;

public class P1_sanitySignInAndOut extends base {
    HomePage homepage;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        homepage = new HomePage();

        driver.manage().window().maximize();
        driver.get(testdata.url);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void P1_signIn(){
        homepage.signIn_Email(testdata.userName, testdata.password);
        screenShot("Login\\P1", "P1_signIn");
        allure_LogAttachment("Login " + testdata.userName, "Login\\P1", "P1_signIn");
        Assert.assertTrue(homepage.checkSignIn());
    }

    @Test
    public void P2_signOut(){
        homepage.signOut();
        screenShot("Login\\P1", "P2_signOut");
        allure_LogAttachment("Sign out", "Login\\P1", "P2_signOut");
        Assert.assertTrue(homepage.checkSignOut());
    }

}
