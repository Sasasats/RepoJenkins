import Forms.CardAuthorization;
import Forms.CardInterests;
import Forms.CardsPage;
import Forms.WelcomePage;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTask1 extends BaseTest {
    private final ISettingsFile testData = new JsonSettingsFile("TestData.json");

    private final WelcomePage welcomePage = new WelcomePage();
    private final CardsPage cardsPage = new CardsPage();
    private final CardAuthorization cardAuthorization = new CardAuthorization();
    private final CardInterests cardInterests = new CardInterests();

    @Test
    public void testCase1() {
        Assert.assertTrue(welcomePage.state().isDisplayed(), "Welkome page not open, start element not displayed!");
        AqualityServices.getLogger().info("Перейти по ссылке к следующей странице (Please click HERE to GO to the next page)");
        welcomePage.clickLink();
        Assert.assertEquals(cardsPage.getNumberPage(), testData.getValue("/numberCard1").toString(), "Wrong page number!");

        AqualityServices.getLogger().info("Выбор 3 случайных интересов, загрузка любого изображения, нажатие кнопки Next");

        cardAuthorization.enterPassword();
        cardAuthorization.enterEmail();
        cardAuthorization.enterDomain();
        cardAuthorization.changeDomainCountry();
        cardAuthorization.clickCheckbox();
        cardAuthorization.goToNextCard();

        Assert.assertEquals(cardsPage.getNumberPage(), testData.getValue("/numberCard2").toString(), "Wrong page number!");

        cardInterests.chooseDifferentInterests(3);
        cardInterests.uploadImage();
        cardInterests.goToNextCard();

        Assert.assertEquals(cardsPage.getNumberPage(), testData.getValue("/numberCard3").toString(), "Wrong page number!");
    }

    @Test
    public void testCase2() {
        Assert.assertTrue(welcomePage.state().isDisplayed(), "Welkome page not open, start element not displayed!");
        AqualityServices.getLogger().info("Перейти по ссылке к следующей странице (Please click HERE to GO to the next page)");
        welcomePage.clickLink();
        Assert.assertEquals(cardsPage.getNumberPage(), testData.getValue("/numberCard1").toString(), "Wrong page number!");

        AqualityServices.getLogger().info("Скрытие окна-формы Help");
        cardsPage.hideHelpWindowForm();
        Assert.assertTrue(cardsPage.isHelpFormHidden());
    }

    @Test
    public void testCase3() {
        Assert.assertTrue(welcomePage.state().isDisplayed(), "Welkome page not open, start element not displayed!");
        AqualityServices.getLogger().info("Перейти по ссылке к следующей странице (Please click HERE to GO to the next page)");
        welcomePage.clickLink();
        Assert.assertEquals(cardsPage.getNumberPage(), testData.getValue("/numberCard1").toString(), "Wrong page number!");

        AqualityServices.getLogger().info("Принятие использования cookie");
        cardsPage.waitCookiesBanner();
        cardsPage.acceptCookies();
        Assert.assertFalse(cardsPage.isCookiesAccepted(), "Сookies not accepted yet");
    }

    @Test
    public void testCase4() {
        Assert.assertTrue(welcomePage.state().isDisplayed(), "Welkome page not open, start element not displayed!");
        AqualityServices.getLogger().info("Перейти по ссылке к следующей странице (Please click HERE to GO to the next page)");
        welcomePage.clickLink();
        Assert.assertEquals(cardsPage.getNumberPage(), testData.getValue("/numberCard1").toString(), "Wrong page number!");

        AqualityServices.getLogger().info("Проверка, что таймер начинает отсчет с нуля после открытия страницы");
        Assert.assertEquals(cardsPage.getTimerValue(), testData.getValue("/timerText").toString(), "Timer not starts at 0!");
    }
}