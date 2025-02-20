
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void constructorShouldThrowExceptionWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 10, 5)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void constructorShouldThrowExceptionWhenNameIsBlank(String invalidName) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(invalidName, 10, 5)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionWhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Thunder", -1, 5)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Lightning", 10, -5)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameShouldReturnCorrectValue() {
        Horse horse = new Horse("Storm", 10, 5);
        assertEquals("Storm", horse.getName());
    }

    @Test
    void getSpeedShouldReturnCorrectValue() {
        Horse horse = new Horse("Storm", 10, 5);
        assertEquals(10, horse.getSpeed());
    }

    @Test
    void getDistanceShouldReturnCorrectValue() {
        Horse horse = new Horse("Storm", 10, 5);
        assertEquals(5, horse.getDistance());
    }

    @Test
    void getDistanceShouldReturnZeroWhenNotSpecified() {
        Horse horse = new Horse("Storm", 10);
        assertEquals(0, horse.getDistance());
    }


    @Test
    void moveShouldCallGetRandomDoubleWithCorrectParams() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Storm", 10, 5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void moveShouldCalculateCorrectDistance(double randomValue) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            Horse horse = new Horse("Storm", 10, 5);
            horse.move();
            assertEquals(5 + 10 * randomValue, horse.getDistance());
        }
    }
}


