package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GooglePage {

    @Step("open url")
    public GooglePage openPage() throws MalformedURLException {
        Selenide.open(new URL("http://google.com"));
        return this;
    }

    @Step("search value")
    public GooglePage searchFor(String text) {
        $(By.name("q")).val(text).pressEnter();
        return this;
    }

    public ElementsCollection results() {
        return $$("#rso [role='text'], #rso [role='link']");
    }
}