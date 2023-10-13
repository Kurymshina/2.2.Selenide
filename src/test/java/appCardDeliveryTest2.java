import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class appCardDeliveryTest2 {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldBeSuccessfullyCompleted() {

        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Ха");
        $$(".menu-item_theme_alfa-on-white").findBy(Condition.text("Салехард")).click();
        $("[data-test-id='date']").click();
        if ((!generateDate(3, "MM").equals(generateDate(7, "MM")))) {
            $("[data-step='1']").click();
        }
        $$("[data-day]").findBy(Condition.text(generateDate(7, "dd"))).click();
        $("[data-test-id='name'] input").setValue("Петров-Водкин Иван");
        $("[data-test-id='phone'] input").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        String planDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldBe(Condition.exactText("Успешно! Встреча успешно забронирована на " + planDate));
    }
}
