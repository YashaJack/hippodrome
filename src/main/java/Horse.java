import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Horse {
    private static final Logger logger = LogManager.getLogger(Horse.class);

    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            logger.error("Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if (name.isBlank()) {
            logger.error("Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.error("Speed is negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.error("Distance is negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;

        logger.debug("Создание Horse, имя [{}], скорость [{}], дистанция [{}]", name, speed, distance);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        double randomFactor = getRandomDouble(0.2, 0.9);
        double newDistance = distance + speed * randomFactor;
        logger.debug("Лошадь [{}] двигается: скорость [{}], случайный множитель [{}], новая дистанция [{}]",
                name, speed, randomFactor, newDistance);
        distance = newDistance;
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}