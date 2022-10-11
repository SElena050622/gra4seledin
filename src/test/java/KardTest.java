import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KardTest {

    String genratDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void kardDeliveryOrder() {
        String date = genratDate(6);
        Selenide.open("http://localhost:9999");
        ElementsCollection items = $$(".tab-item");
        items.find(Condition.exactText("Встреча успешно забронирована на 17.10.2022")).click();
        //$("[name = 'city']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[name = 'city']").setValue("Казань");
        $("[name = 'date']").setValue("2022-10-17");
        $("[name = 'name']").setValue("Ив Иван");
        $("[name = 'phone']").setValue("+79998888888");
        SelenideElement blok = $("[data-test-id = 'agreement']");
        blok.$("[name = 'checkbox']").setValue("checkbox_icon");
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
