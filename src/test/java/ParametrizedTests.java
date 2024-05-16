import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;

public class ParametrizedTests
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
    @DisplayName("Тестирование зависимости URL от выбранного языка")
    @CsvFileSource(resources = "/Langs.csv")
    @ParameterizedTest()
    void languageTest(String language, String link) {
        open("https://javarush.com/");
        $(".language-switcher-item").click();
        $(".language-switcher").$(byText(language)).click();
        webdriver().shouldHave(currentFrameUrl(link));
    }
    static Stream<Arguments> sectionsForumTest(){
        return Stream.of( Arguments.of( List.of(
                "О JavaRush", "Как пользоваться курсом", "Отзывы", "FAQ", "Контакты", "Оферта") ) );
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
