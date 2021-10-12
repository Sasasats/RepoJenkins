package Forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {
    private ILink link = getElementFactory().getLink(By.xpath("//div[@class='view__row']//a[@class='start__link']"), "start link");

    public WelcomePage() {
        super(By.xpath("//button[@type='button']"), "start button");
    }

    public void clickLink() {
        link.clickAndWait();
    }
}
