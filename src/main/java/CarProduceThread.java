import java.time.LocalDate;
import java.util.Random;

public class CarProduceThread extends Thread {
    private static final int MS_AS_ONE_DAY = 100;
    private static final int MAX_DAYS = 5;
    private static final int MAX_CARS = 10;
    private String name;

    public CarProduceThread(String name) {
        this.name = name;
    }

    private static int getRandomInterval() {
        Random random = new Random();
        return random.nextInt(CarProduceThread.getMaxDays() * CarProduceThread.getMsAsOneDay());
    }

    private static Model getRandomModel() {
        Random random = new Random();
        int index = random.nextInt(Models.getSize()-1);
        return Models.getModel(index);
    }

    public static int getMaxDays() {
        return MAX_DAYS;
    }

    public static int getMsAsOneDay() {
        return MS_AS_ONE_DAY;
    }

    public static int getMaxCars() {
        return MAX_CARS;
    }

    public synchronized void start(Cars cars) throws InterruptedException {
        while (cars.getCars().size() < getMaxCars()) {
            Car car = new Car(getRandomModel());
            cars.getCars().add(car);
            Thread.sleep(getRandomInterval());
            System.out.println("Поступил " + car);
        }
        super.start();
    }


}
