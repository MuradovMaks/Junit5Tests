import com.codeborne.selenide.*;
import org.checkerframework.checker.signature.qual.PrimitiveType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestODemoqa
{
    @BeforeAll
    static void setUp()
    {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
    static Stream<Arguments>demoqa()
    {
        return Stream.of(
        Arguments.of(
                List.of("Elements","Forms","Alerts, Frame & Windows",
                        "Widgets","Interactions","Book Store Application")
                ));

    }

    @MethodSource("demoqa")
    @ParameterizedTest(name = "Проверка соответствия имени карточек {0} действительным именам")
    void demoqaShoudHaveCategoryList(List<String>Category)
    {
        open("https://demoqa.com/");
        $$(".card-body h5").shouldHave(CollectionCondition.texts(Category));
    }
    @DisplayName("Проверка авторизации в XYZ Bank")
    @ValueSource(strings = {
            "Harry Potter",
            "Ron Weasly",
            "Albus Dumbledore",
            "Neville Longbottom"
    })
    @ParameterizedTest()
    void checkCustomerLoginTest(String name) {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        $(".btn-lg").shouldBe(text("Customer Login")).click();
        $("#userSelect").click();
        $("#userSelect").selectOption(name);
        $("[type='submit']").click();
        $(".fontBig").shouldHave(text(name));

    }
    @ValueSource(strings = {"Sergey", "Petr", "Alexander, Evgeniy"})
    @DisplayName("Test for field Full Name")
    @ParameterizedTest(name = "Test for field Full Name на примере {0}")
    void textBoxFullNameFieldTest(String value) {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(value);
        $("#submit").click();
        $("#output").shouldHave(text("Name:" + value));
    }



}
