    package Listmethod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public  class ListMethod extends Utils {
    LoadProbs loadProbs = new LoadProbs();
    SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src\\main\\java\\Resource\\BrowserDriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximize the window
        driver.manage().window().fullscreen();
        //set implicity wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(loadProbs.getProperty("url"));
    }

    @AfterMethod

    public void close() {
        driver.quit();
    }


    @Test

    public void addProductsToCompareList() throws InterruptedException {
        String Expectedproduct1title = getTextFromEement(By.xpath("//h2/a [@href=\"/build-your-own-computer\"]"));
        String Expectedproduct2title = getTextFromEement(By.xpath("//h2/a [@href=\"/apple-macbook-pro-13-inch\"]"));
        clickElement(By.xpath("//div[@data-productid=\"1\"]/div[2]/div[3]/div[2]/input[@value='Add to compare list']"));
        String ExpectedMessage = "The product has been added to your product comparison";
        String Actualmessage = getTextFromEement(By.xpath("//div[@class='bar-notification success']"));
        softAssert.assertEquals(Actualmessage, ExpectedMessage);
        Thread.sleep(5000);//temporary
        clickElement(By.xpath("//div[@data-productid=\"4\"]/div[2]/div[3]/div[2]/input[@value='Add to compare list']"));
        String ExpectedMessage2 = "The product has been added to your product comparison";
        String Actualmessage2 = getTextFromEement(By.xpath("//div[@class='bar-notification success']"));
        softAssert.assertEquals(Actualmessage2, ExpectedMessage2);
        clickElement(By.linkText("product comparison"));
        String Actualproduct1title = getTextFromEement(By.linkText("Build your own computer"));
        String Actualproduct2title = getTextFromEement(By.linkText("Apple MacBook Pro 13-inch"));
        softAssert.assertEquals(Actualproduct1title, Expectedproduct1title);
        softAssert.assertEquals(Actualproduct2title, Expectedproduct2title);
        clickElement(By.className("clear-list"));
        String Expectedconfirmationmessage = "You have no items to compare.";
        String Actualconfirmationmessage = getTextFromEement(By.className("no-data"));
        softAssert.assertEquals(Actualconfirmationmessage, Expectedconfirmationmessage);
        softAssert.assertAll();

    }


    @Test
    public  void addcommentonnews() throws InterruptedException {
        clickElement(By.className("read-more"));
       enterText(By.className("enter-comment-title"), "Testcomment");
        enterText(By.className("enter-comment-text"), "Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        String Expectedcommenttitle = "Testcomment";
        System.out.println(Expectedcommenttitle);
        clickElement(By.xpath("//input[@class=\"button-1 news-item-add-comment-button\"]"));
        String Expectedconfirmationmsg = "News comment is successfully added.";
        String Actualconfirmationmsg = getTextFromEement(By.xpath("//div[@class='result']"));
        softAssert.assertEquals(Actualconfirmationmsg,Expectedconfirmationmsg);
        List<WebElement> al = driver.findElements(By.xpath("//div[@class=\"comment news-comment\"]"));
        System.out.println(al.size());
        WebElement lastelement=al.get(al.size()-1);
        String lastecomment = lastelement.getText();


        softAssert.assertEquals(lastecomment.contains("Testcomment"),Expectedcommenttitle.contains("Testcomment "));

        softAssert.assertAll();
    }
    @Test
    public  void searchproductbynamefromseachbox(){

        enterText(By.id("small-searchterms"),loadProbs.getProperty("searchitems"));
        clickElement(By.xpath("//input[@class='button-1 search-box-button']"));
        List<WebElement> productlist = driver.findElements(By.className("item-box"));
        System.out.println(productlist.size());
        for(WebElement e:productlist){
            //System.out.println(e.getText());
            String Expectedproduct = "Nike";
            if(productlist.size()>0){
                    if(e.getText().contains(loadProbs.getProperty("searchitems"))){
                   softAssert.assertEquals(e.getText().contains(loadProbs.getProperty("searchitems")),Expectedproduct.contains("Nike"));
                    System.out.println("search products are " + e.getText());
                }else{
                    System.out.println(loadProbs.getProperty("searchitems")+" searchable product found");
                    softAssert.assertEquals(e.getText().contains("loadProbs.getProperty(\"searchitems\""),Expectedproduct);

                 }

        }
        }
        softAssert.assertAll();
    }
}

