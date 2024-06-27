package app.google;

import app.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.GooglePage;

import java.net.MalformedURLException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;

public class GoogleSearchTest extends BaseTest {

    @Test(priority = 1, description = "test google search")
    @Description("""
            Test Description:
            1. go to google page
            2. make search
            3. validate result""")
    public void userCanSearch() throws MalformedURLException {
        var testData = "facebook";
        new GooglePage()
                .openPage()
                .searchFor(testData)
                .results()
                .shouldHave(sizeGreaterThan(10))
                .get(0)
                .shouldHave(text(testData));
    }
}
