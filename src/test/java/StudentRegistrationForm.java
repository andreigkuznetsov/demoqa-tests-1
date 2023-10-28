import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationForm {

    File image = new File("src/test/resources/pic.jpg");

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void fillRegFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#submit").scrollIntoView(true);
        //заполняем форму и отправляем ее
        $("#firstName").setValue("Andrey");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("andrey@Smith.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8950789456");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("9");
        $(".react-datepicker__year-select").selectOptionByValue("1996");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("c");
        $(byText("Economics")).click();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(image);
        $("#currentAddress").setValue("Russia");
        $("#state").click();
        $(byText("Rajasthan")).click();
        $("#city").click();
        $(byText("Jaiselmer")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name"))
                .parent().shouldHave(text("Andrey Smith"));
        $(".table-responsive").$(byText("Student Email"))
                .parent().shouldHave(text("andrey@Smith.com"));
        $(".table-responsive").$(byText("Gender"))
                .parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile"))
                .parent().shouldHave(text("8950789456"));
        $(".table-responsive").$(byText("Date of Birth"))
                .parent().shouldHave(text("30 October,1996"));
        $(".table-responsive").$(byText("Subjects"))
                .parent().shouldHave(text("Economics"));
        $(".table-responsive").$(byText("Hobbies"))
                .parent().shouldHave(text("Sports"));
        $(".table-responsive").$(byText("Picture"))
                .parent().shouldHave(text("pic.jpg"));
        $(".table-responsive").$(byText("Address"))
                .parent().shouldHave(text("Russia"));
        $(".table-responsive").$(byText("State and City"))
                .parent().shouldHave(text("Rajasthan Jaiselmer"));
        $("#closeLargeModal").click();
    }
}
