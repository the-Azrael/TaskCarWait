import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final int MS_AS_ONE_DAY = 100;
    private static final int MAX_DAYS = 7;
    private static final List<Car> cars = new LinkedList<>();
    private static final int MAX_CARS = 10;

    private static Model getRandomModel() {
        Random random = new Random();
        int index = random.nextInt(Models.getSize()-1);
        return Models.getModel(index);
    }

    private static int getRandomInterval() {
        Random random = new Random();
        return random.nextInt(MAX_DAYS * MS_AS_ONE_DAY);
    }

    private static Thread getThreadCarArrival() {
        return new Thread(() -> {
             synchronized (cars) {
                while (cars.size() < MAX_CARS) {
                    Car car = new Car(getRandomModel(), LocalDate.now());
                    cars.add(car);
                    System.out.println("Поступил автомобиль " + " " + car);
                    try {
                        Thread.sleep(getRandomInterval());
                    } catch (InterruptedException e) {
                        e.printStackTrace(System.out);
                    }
                    cars.notify();
                }
            }
        });
    }

    private static Thread getThreadCarBuy(int buyerID) {
        return new Thread(() -> {
            boolean hasBuy = false;
            int visitCount = 1;
            synchronized (cars) {
                System.out.println("Покупатель " + buyerID + " пришел в магазин в " + visitCount + " раз");
                visitCount++;
                while(!hasBuy || visitCount > 2) {
                    if (cars.isEmpty()) {
                        System.out.println("Покупатель " + buyerID + " увидел, что машин нет");
                        try {
                            cars.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        hasBuy = true;
                        Car car = cars.get(0);
                        cars.remove(car);
                        System.out.println("Покупатель " + buyerID +" купил машину " + car);
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < MAX_CARS + 1; i++) {
            Thread buy = getThreadCarBuy(i);
            buy.start();
        }
        Thread arrival = getThreadCarArrival();
        arrival.start();
    }

}
