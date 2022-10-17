import com.codeborne.selenide.*;
import org.jsoup.select.Selector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class KardTest {

    String genratDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        closeWebDriver();
    }
    @Test
    void kardDeliveryOrder() {
        Selenide.open("http://localhost:9999");
        String date = genratDate(6);
        $("[data-test-id = 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id = 'date'] input").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Ив Иван");
        $("[data-test-id = 'phone'] input").setValue("+79998888888");
        $("[data-test-id = 'agreement']").click();
        $$("button").find(text("Забронировать")).click();
        //$(Selectors.byText("Успешно!" + "Встреча успешно забронирована на " + date)).shouldBe(visible, Duration.ofSeconds(15));
        //$(Selectors.byText("Встреча успешно забронирована на " + date)).shouldBe(visible, Duration.ofSeconds(15));
        $("[name = 'notification__title']").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(text("Успешно!"));
        //$("[name = 'notification__content']").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(text("Встреча успешно забронирована на " + date));
        //$("[data-test-id = 'notification'] .notification__title").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(text("Успешно!"));
        //SelenideElement blok = $("[data-test-id = 'notification']");
        //blok.$("[name = 'notification__title']").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(text("Успешно!"));
        //blok.$("[name = 'notification__content']").shouldBe(visible, Duration.ofSeconds(15)).shouldBe(text("Встреча успешно забронирована на " + date));
        //$(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
