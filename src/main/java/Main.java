import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        logger.info("Начало скачек. Количество участников: {}", horses.size());

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            try {
                watch(hippodrome);
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Ошибка во время сна потока", e);
            } catch (Exception e) {
                logger.error("Ошибка при отображении хода скачек", e);
            }
        }

        String winnerName = hippodrome.getWinner().getName();
        logger.info("Окончание скачек. Победитель: {}", winnerName);
        System.out.println(winnerName + " wins!");
    }

    private static void watch(Hippodrome hippodrome) {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}