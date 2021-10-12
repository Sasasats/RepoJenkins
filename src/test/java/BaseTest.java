import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private final ISettingsFile testData = new JsonSettingsFile("TestData.json");

    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getLogger().info("Переход на сайт по ссылке https://userinyerface.com/game.html%20target=");
        getBrowser().goTo(testData.getValue("/URL").toString());
        getBrowser().maximize();
        getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}