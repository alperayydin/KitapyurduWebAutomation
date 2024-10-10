package Pages;

import Pages.Pages;
import org.openqa.selenium.WebDriver;

public class MainPage extends Pages {
    public final String login = "//a[.='Giriş Yap']";
    public final String search = "search-input";
    public final String searchButton = ".button-search";
    public final String book ="div[class=\"name\"] > a[title=\"Küçük Prens\"]";
    public final String book2 = "//span[contains(.,'Suç ve Ceza (2 Cilt Takım) (Ciltli, İplik Dikişli)')]";
    public MainPage(WebDriver driver){
        super(driver);
    }

}
