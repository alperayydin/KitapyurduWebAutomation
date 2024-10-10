package Pages;

import org.openqa.selenium.WebDriver;

public class LoginPage extends Pages {
    public final String mail = "login-email";
    public final String password = "login-password";
    public final String button = ".ky-btn-orange";
    public final String errorMessage = ".ky-error";

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
