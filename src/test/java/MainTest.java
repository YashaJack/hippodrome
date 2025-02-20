import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Этот тест отключен, так как может замедлять выполнение тестов. Запускайте вручную.")
    void mainShouldCompleteWithin22Seconds() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}