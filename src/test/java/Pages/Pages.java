package Pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Pages {
    WebDriver driver;
    public Pages(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Element beklenir (xpath).")
    public void waitElementXpath(String elementId){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementId)));
    }
    @Step("Element beklenir (id).")
    public void waitElementId(String elementId){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
    }
    @Step("Element beklenir (css).")
    public void waitElementCss(String elementId){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementId)));
    }
    @Step("Butona tıklanır (css).")
    public void clickCss(String elementId){
        driver.findElement(By.cssSelector(elementId)).click();
    }
    @Step("Butona tıklanır (id).")
    public void clickId(String elementId){
        driver.findElement(By.id(elementId)).click();
    }
    @Step("Butona tıklanır (xpath).")
    public void clickXpath(String elementId){
        driver.findElement(By.xpath(elementId)).click();
    }
    @Step("Alan doldurulur.")
    public void fillBox (String elementId, String text){
        driver.findElement(By.id(elementId)).sendKeys(text);
        screenshot();
    }
    @Step("Doğru karakter alınır.")
    public String getTrueName(String elementId){
        String value = driver.findElement(By.cssSelector(elementId)).getText();
        screenshot();
        return value;
    }
    @Step("Element attritube alınır")
    public String getAttritube(String elementId, String attr){
        String value = driver.findElement(By.cssSelector(elementId)).getAttribute(attr);
        return value;
    }
    @Step("Hata mesajı alınır")
    public String getErrorMessage(String elementId){

        String value2 = driver.findElement(By.cssSelector(elementId)).getText();
        screenshot();
        return value2;

    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    
}
