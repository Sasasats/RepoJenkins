package Forms;

import Utils.UploadUtils;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CardInterests extends Form {
    private final UploadUtils uploadUtils = new UploadUtils();

    private final IButton lUploadAvatar = getElementFactory().getButton(By.xpath("//p//a[@class='avatar-and-interests__upload-button']"), "link for upload avatar");
    private List<ICheckBox> checkBoxesInterests;
    private ICheckBox checkBoxUnselectAll;
    private final IButton buttonNext = getElementFactory().getButton(By.xpath("//div[@class='avatar-and-interests-page__buttons-row']//button[contains(@class,'button--white')]"), "button next");

    private final static String xpathToCheckBoxesInterests = "//div[@class='avatar-and-interests__interests-list']//label";
    private final static String filterUnselectAll = "interest_unselectall";

    public CardInterests() {
        super(By.xpath("//div[@class='page-indicator']"), "page indicator");
    }

    private void selectionInterests() {
        checkBoxesInterests = getElementFactory().findElements(By.xpath(xpathToCheckBoxesInterests), ElementType.CHECKBOX);

        checkBoxUnselectAll = checkBoxesInterests.stream().filter(checkBox -> filterUnselectAll.equals(checkBox.getAttribute("for"))).findFirst().orElseThrow();
        checkBoxesInterests.remove(checkBoxUnselectAll);
        checkBoxesInterests.removeIf(checkBox -> Objects.equals(checkBox.getAttribute("for"), filterUnselectAll));
    }

    private void unselectAll() {
        checkBoxUnselectAll.clickAndWait();
    }

    public void chooseDifferentInterests(int number) {
        Random random = new Random();

        selectionInterests();
        unselectAll();

        for (int i = 0; i < number; i++) {
            checkBoxesInterests.remove(random.nextInt(checkBoxesInterests.size())).clickAndWait();
        }
    }

    public void uploadImage() {
        lUploadAvatar.clickAndWait();
        uploadUtils.uploadAvatar();
    }

    public void goToNextCard() {
        buttonNext.clickAndWait();
    }
}