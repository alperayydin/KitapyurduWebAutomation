package Pages;

import Pages.Pages;
import org.openqa.selenium.WebDriver;

public class BookPage extends Pages {
    public final String addToCart = "button-cart";
    public final String cart = "h4.common-sprite";
    public final String goToCart = "js-cart";
    public final String checkout = "js-checkout";
    public final String continueWithoutUser = "//a[contains(text(),'Üye Olmadan Devam Et')]";
    public final String cartErrorMessage = ".alert";
    public final String cartSuccesfulMessage = "span[class=\"lh-1\"]";
    public BookPage(WebDriver driver){
        super(driver);
    }

}