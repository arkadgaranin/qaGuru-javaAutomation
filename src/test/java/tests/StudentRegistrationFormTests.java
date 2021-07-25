package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

  @BeforeAll
  static void setup() {
    Configuration.startMaximized = true;
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://demoqa.com";
  }

  @Test
  void fillingRegistrationFormTest() {

    open("/automation-practice-form");
    $("#firstName").setValue("Arkadiy");
    $("#lastName").setValue("Garanin");
    $("#userEmail").setValue("arkgaranin@gmail.com");
    $("[for='gender-radio-1']").click();//Или так: $("[name=gender][value=Other]").parent().click();
    $("#userNumber").setValue("9772601157");

    // Заполнение даты рождения кликами
    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").selectOption("April");
    $(".react-datepicker__year-select").selectOption("1990");
    $("[aria-label='Choose Thursday, April 26th, 1990']").click();

    /*Пробовал заполнить поле даты рождения с помощью js функции (заполняет, но как только переходит к следующему полю-
    сбрасывает дату в дефолтную 06 Jun 2021 почему-то)
    setDateByName("$('#dateOfBirthInput').val('26 Apr 1990')", "26 Apr 1990"); */

    $("#subjectsInput").setValue("En").pressEnter();
    $("#subjectsInput").setValue("Co").pressEnter();

    $("[for='hobbies-checkbox-1']").click();
    $("[for='hobbies-checkbox-2']").click();
    $("[for='hobbies-checkbox-3']").click();

    $("#uploadPicture").uploadFile(new File("src/test/resources/Bart.png"));

    $("#currentAddress").setValue("Moscow region, Odintsovo city, Severnaya street");
    $("#react-select-3-input").setValue("Ha").pressEnter();
    $("#react-select-4-input").setValue("Ka").pressEnter();

    // Submit Registration Form
    $("#submit").scrollTo().click();

    // Проверка регистрационных данных в попапе
    $(".table-responsive").shouldHave(text("Arkadiy Garanin"), text("arkgaranin@gmail.com"), text("Male"),
        text("9772601157"), text("26 April,1990"), text("English, Computer Science"), text("Sports, Reading, Music"),
        text("Bart.png"), text("Moscow region, Odintsovo city, Severnaya street"), text("Haryana Karnal"));
  }

  /**
   * Функция для заполнения поля "Date of Birth с помощью JS (не используется, т.к работает некорректно)"
   * @param name
   * @param date
   */
  void setDateByName (String name, String date) {
    executeJavaScript(String.format("$('#dateOfBirthInput').val('26 Apr 1990')", name, date));
  }
}