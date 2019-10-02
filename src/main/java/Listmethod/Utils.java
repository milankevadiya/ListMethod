package Listmethod;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils extends BasePage {

    //1. method for send value in textbox
    public static void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    //2. method for click on element
    public static void clickElement(By by) {
        driver.findElement(by).click();
    }

    //3. method for get text of element
    public  static String getTextFromEement(By by) {
        String Text = driver.findElement(by).getText();
        return Text;

    }

    //4. method for wait till element clickable
    public static void waitForClickable(By by, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    //5. method for wait till element visible
    public static void waitForElementVisible(By by, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //6. method for wait till Alert present
    public static void waitForAlertPresent(long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //7. method for select by visible value
    public static void selectVisibleValue(By by, String value) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
        select.getFirstSelectedOption();
    }

    //8. method for select by visible text
    public static void selectVisibleText(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    //9. method for select by index
    public static void selectbyIndex(By by, int value) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(value);
    }

    //10. method for clear value of inputfield
    public static void clearinputfield(By by) {
        driver.findElement(by).clear();
    }

    //11. method for check element present or not
    public static void elementPresent(By by) {
        List<WebElement> dynamicElement = driver.findElements(by);

        if (dynamicElement.size() != 0) {
            System.out.println(dynamicElement.size());
        } else {
            System.out.println("element not avilbale");
        }
    }

    //12. method for check webelement is displayed or not
    public static boolean elementDisplayed(By by) {
         boolean status = driver.findElement(by).isDisplayed();
        return status;

    }

    //13. method for create random date
    public static String randomdate() {
        //for create randomdate
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }

    //14. method for get css value
    public static String cssValue(By by, String text) {
        String css = driver.findElement(by).getCssValue(text);
        return css;
    }

    //15. method for get attribute value
    public static String getAttribute(By by, String text) {
        String attribute = driver.findElement(by).getAttribute(text);
        return attribute;

    }

    //16. method for verify element is enable or not
    public static boolean isEnabled(By by) {
        boolean enable = driver.findElement(by).isEnabled();
        return enable;

    }

    //17. method for verify element is displayed or not
    public static boolean isDisplayed(By by) {
        boolean display = driver.findElement(by).isDisplayed();
        return display;

    }

    //18. method for verify element is selected or not(ex.  checkboxes, radio buttons and dropdowns)
    public static boolean isSelected(By by) {
        boolean select = driver.findElement(by).isSelected();
        return select;

    }


    //19. method for scroll window
    public static void scrollingWindow() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
    }

    //20. method for scroll till element
    public static void scrollTillElement(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    //21. method for scrolling to bottom of page
    public static void scrollingToBottomofAPage() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //22. method for screenshot2
    public static void getScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // FileUtils.copyFile(scrFile, new File("E:\\Unique testing class\\Nopotest-master\\screnshot"+randomdate()+".png"), true);
        FileUtils.copyFile(scrFile, new File("E:\\Unique testing class\\Nopotest-master\\screnshot"+randomdate()+".png"));

    }

}
