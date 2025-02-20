import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void constructorShouldThrowExceptionWhenHorsesIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionWhenHorsesIsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesShouldReturnSameList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse" + i, i, i * 2));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveShouldCallMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerShouldReturnHorseWithMaxDistance() {
        List<Horse> horses = List.of(
                new Horse("Spirit", 10, 20),
                new Horse("Blaze", 12, 25),
                new Horse("Storm", 15, 30)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.get(2), hippodrome.getWinner());
    }
}