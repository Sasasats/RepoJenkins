package Forms;

import Utils.RandomUtils;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

public class CardAuthorization extends Form {
    private final RandomUtils randomUtils = new RandomUtils();

    private final static String xpathToInputTextField = "//input[@placeholder='%s']";

    private final ITextBox textBoxPassword = getElementFactory().getTextBox(By.xpath(String.format(xpathToInputTextField, "Choose Password")), "password");
    private final ITextBox textBoxEmail = getElementFactory().getTextBox(By.xpath(String.format(xpathToInputTextField, "Your email")), "email");
    private final ITextBox textBoxDomain = getElementFactory().getTextBox(By.xpath(String.format(xpathToInputTextField, "Domain")), "domain");
    private final IComboBox comboBoxDomainCountry = getElementFactory().getComboBox(By.xpath("//div[@class='dropdown__opener']"), "dropdown opener");
    private final ICheckBox chekBoxDoNotAccept = getElementFactory().getCheckBox(By.xpath("//label//span[@class='checkbox__box']"), "checkbox do not accept Terms & Conditions");
    private final ILink linkNext = getElementFactory().getLink(By.xpath("//div//a[@class='button--secondary']"), "button next");

    private List<IComboBox> getComboBoxDomainCountryItems(){
        return getElementFactory().findElements(By.xpath("//div[@class='dropdown__list']//div"), ElementType.COMBOBOX);
    }

    public CardAuthorization() {
        super(By.xpath("//form//div[@class='page-indicator']"), "page indicator");
    }

    public void enterPassword() {
        textBoxPassword.clearAndType(randomUtils.getRandomString(10));
    }

    public void enterEmail() {
        textBoxEmail.clearAndType(randomUtils.getRandomStringEmailDomain(5));
    }

    public void enterDomain() {
        textBoxDomain.clearAndType(randomUtils.getRandomStringEmailDomain(5));
    }

    public void clickCheckbox() {
        chekBoxDoNotAccept.clickAndWait();
    }

    public void changeDomainCountry() {
        Random random = new Random();
        comboBoxDomainCountry.clickAndWait();
        List<IComboBox> comboBoxDomainCountryItems = getComboBoxDomainCountryItems();
        comboBoxDomainCountryItems.get(random.nextInt(comboBoxDomainCountryItems.size())).clickAndWait();
    }

    public void goToNextCard() {
        linkNext.clickAndWait();
    }
}