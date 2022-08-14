import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final int MS_AS_ONE_DAY = 100;
    private static final int MS_AS_WAIT = 100;
    private static final int MAX_DAYS = 5;
    private static final List<Car> cars = new ArrayList<>();
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
            while (cars.size() < MAX_CARS) {
                synchronized (cars) {
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
            String threadName = Thread.currentThread().getName();
            synchronized (cars) {
                while(!hasBuy) {
                    System.out.println(threadName + ": "  + " Зашел в автосалон");
                    if (cars.isEmpty()) {
                        System.out.println(threadName + ": "  + " Машин нет");
                        try {
                            try {
                                Thread.sleep(MS_AS_WAIT);
                            } catch (InterruptedException e) {
                                e.printStackTrace(System.out);
                            }
                            cars.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace(System.out);
                        }
                    } else {
                        hasBuy = true;
                        Car car = cars.get(0);
                        cars.remove(car);
                        System.out.println(threadName + ": "  + " уехал на машине " + car);
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> clients = new ArrayList<>();
        for (int i = 1; i < MAX_CARS + 1; i++) {
            Thread client = getThreadCarBuy(i);
            client.setName("Покупатель" + i);
            clients.add(client);
        }
        Thread arrival = getThreadCarArrival();
        arrival.start();
        for (Thread client : clients) {
            client.start();
        }
    }

}
