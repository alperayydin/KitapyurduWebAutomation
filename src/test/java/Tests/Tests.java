package Tests;

import Pages.AccountPage;
import Pages.BookPage;
import Pages.LoginPage;
import Pages.MainPage;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.AssertJUnit.assertEquals;
@Feature("Kitap yurdu test senaryoları")
public class Tests {
    private WebDriver driver;
    private AccountPage accountPage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private BookPage bookpage;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        accountPage = new AccountPage(driver);
        loginPage = new LoginPage(driver);
        bookpage = new BookPage(driver);
        driver.get("https://www.kitapyurdu.com/");
    }

    @Test(description = "Başarılı kullanıcı girişi")
    public void loginSuccesful(){
        mainPage.waitElementXpath(mainPage.login);
        mainPage.clickXpath(mainPage.login);
        loginPage.waitElementId(loginPage.mail);
        loginPage.fillBox(loginPage.mail, "alper141111@gmail.com");
        loginPage.fillBox(loginPage.password, "Alper1234");
        loginPage.clickCss(loginPage.button);
        accountPage.waitElementCss(accountPage.hesabim);
        String value = accountPage.getTrueName(accountPage.hesabim);
        assertEquals("Hesabım", value);
    }

    @Test(description = "Başarısız kullanıcı girişi")
    public void loginUnsuccesful(){
        mainPage.waitElementXpath(mainPage.login);
        mainPage.clickXpath(mainPage.login);
        loginPage.waitElementId(loginPage.mail);
        loginPage.fillBox(loginPage.mail, "alper141111@gmail.com");
        loginPage.fillBox(loginPage.password, "random");
        loginPage.clickCss(loginPage.button);
        loginPage.waitElementCss(loginPage.errorMessage);
        String value = loginPage.getErrorMessage(loginPage.errorMessage);
        assertEquals("E-Posta Adresi ya da şifreniz yanlış. Şifrenizi girerken büyük küçük harf ayrımına dikkat ediniz.", value);
    }

    @Test(description = "Doğru arama kontrolü")
    public void search(){
        mainPage.waitElementId(mainPage.search);
        mainPage.fillBox(mainPage.search, "Küçük prens");
        mainPage.clickCss(mainPage.searchButton);
        mainPage.waitElementCss(mainPage.book);
        String value = mainPage.getAttritube(mainPage.book, "title").toLowerCase();
        assertEquals("küçük prens",value);

    }

    @Test(description = "Sepete ürün ekleme kontrolü")
    public void cart()throws InterruptedException{
        mainPage.waitElementId(mainPage.search);
        mainPage.fillBox(mainPage.search, "Küçük prens");
        mainPage.clickCss(mainPage.searchButton);
        mainPage.waitElementCss(mainPage.book);
        mainPage.clickCss(mainPage.book);
        bookpage.waitElementId(bookpage.addToCart);
        bookpage.clickId(bookpage.addToCart);
        Thread.sleep(1000);
        bookpage.waitElementCss(bookpage.cart);
        bookpage.clickCss(bookpage.cart);
        bookpage.waitElementId(bookpage.goToCart);
        bookpage.clickId(bookpage.goToCart);
    }

    @Test(description = "Sepetteki ürünün 100TL'nin altında olduğunun kontorlü")
    public void checkoutError() throws InterruptedException{
        mainPage.waitElementId(mainPage.search);
        mainPage.fillBox(mainPage.search, "Küçük prens");
        mainPage.clickCss(mainPage.searchButton);
        mainPage.waitElementCss(mainPage.book);
        mainPage.clickCss(mainPage.book);
        bookpage.waitElementId(bookpage.addToCart);
        bookpage.clickId(bookpage.addToCart);
        Thread.sleep(1000);
        bookpage.waitElementCss(bookpage.cart);
        bookpage.clickCss(bookpage.cart);
        bookpage.waitElementId(bookpage.checkout);
        bookpage.clickId(bookpage.checkout);
        bookpage.waitElementXpath(bookpage.continueWithoutUser);
        bookpage.clickXpath(bookpage.continueWithoutUser);
        String value = bookpage.getErrorMessage(bookpage.cartErrorMessage);
        assertEquals("Sipariş verebilmeniz için sepet tutarı en az 100,00 olmalıdır.",value);
    }

    @Test(description = "Ödeme ekranı kontrolü")
    public void checkoutSuccesful() throws InterruptedException {
        mainPage.waitElementId(mainPage.search);
        mainPage.fillBox(mainPage.search, "Suç ve ceza");
        mainPage.clickCss(mainPage.searchButton);
        mainPage.waitElementXpath(mainPage.book2);
        mainPage.clickXpath(mainPage.book2);
        bookpage.waitElementId(bookpage.addToCart);
        bookpage.clickId(bookpage.addToCart);
        Thread.sleep(1000);
        bookpage.waitElementCss(bookpage.cart);
        bookpage.clickCss(bookpage.cart);
        bookpage.waitElementId(bookpage.checkout);
        bookpage.clickId(bookpage.checkout);
        bookpage.waitElementXpath(bookpage.continueWithoutUser);
        bookpage.clickXpath(bookpage.continueWithoutUser);
        String value = bookpage.getTrueName(bookpage.cartSuccesfulMessage);
        assertEquals("Teslim Noktasından Al",value);
    }


    @AfterMethod
    public void tearDown (){
        driver.quit();
    }

}
