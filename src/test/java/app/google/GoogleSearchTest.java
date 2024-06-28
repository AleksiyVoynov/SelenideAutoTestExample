package app.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.GooglePage;

import java.net.MalformedURLException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class GoogleSearchTest {

    private final String testData = "facebook";
    GooglePage googlePage;
    @BeforeClass
    @Step("setting up web driver")
    @Parameters({"browser"})
    public void setUp(@Optional("default") String browser) throws MalformedURLException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.headless = false;
        Configuration.timeout = 3000;
        if(browser.equals("default")) {
            open();
        } else {
            Configuration.browser = "chrome";
        }
         googlePage = new GooglePage().openPage();
    }

    @Test(priority = 1, description = "test google search 1")
    @Description("""
            Test Description:
            1. go to google page
            2. make search
            3. validate result""")
    public void userCanSearch1() {
        googlePage.searchFor(testData)
                .results()
                .shouldHave(sizeGreaterThan(10))
                .get(0)
                .shouldHave(text(testData))
                .shouldBe(visible);
    }

    @Test(priority = 2, description = "test google search 2")
    @Description("""
            Test Description:
            1. go to google page
            2. make search
            3. validate result""")
    public void userCanSearch2() {
        googlePage.searchFor(testData)
                .results()
                .get(10)
                .scrollTo()
                .shouldHave(or("first or second value", text(testData), text("google")))
                .shouldBe(visible);
    }
}
