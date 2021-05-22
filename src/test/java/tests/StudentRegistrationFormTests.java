package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

  @BeforeAll
  static void configTests() {
    Configuration.startMaximized = true;
    Configuration.browser = "chrome";
  }

  @BeforeEach
  void openDemoQaPage() {
    open("https://demoqa.com/automation-practice-form");
  }

  @AfterEach
  void closeBrowser() {
    closeWebDriver();
  }

  @Test
  void FillingRegistrationFormTest() {
    $("#firstName").setValue("Arkadiy");
    $("#lastName").setValue("Garanin");
    $("#userEmail").setValue("arkgaranin@gmail.com");
    $("[for='gender-radio-1']").click();
    $("#userNumber").setValue("9772601157");

    // --Поле календаря даты рождения
    //$("#dateOfBirthInput").click();
    //$(".react-datepicker__month-select").selectOption("April");
    //$(".react-datepicker__year-select").selectOption("1990");

    // --Поле с автоподстановкой значений
    //$(".subjects-auto-complete__value-container").click();
    //$(".subjects-auto-complete__value-container").setValue("Computer Science");

    $("[for='hobbies-checkbox-1']").click();
    $("[for='hobbies-checkbox-2']").click();
    $("[for='hobbies-checkbox-3']").click();

    $("#uploadPicture").uploadFile(new File("src/files/wallpaper-2265992.jpg"));
    $("#currentAddress").setValue("Moscow region, Odintsovo city, Severnaya street");

    // -- Выбор значений из 2-х нижних селектов
    //$($x("//div[@class=' css-1pahdxg-control']/div[@class=' css-1hwfws3']")).click();
    //$(".css-1gtu0rj-indicatorContainer > .css-19bqh2r").selectOption("Rajasthan");
  }

}