import java.util.List;

public class BuyerThread extends Thread {
    private static final int MAX_CLIENTS = 10;
    private static final int MAX_VISITS = 2;
    private static final int WAIT_TIME = 100;
    private String name;
    private Car car = null;
    private boolean hasBuy = false;
    private int visitCount = 1;

    public BuyerThread(String name) {
        this.name = name;
    }

    public static int getMaxVisits() {
        return MAX_VISITS;
    }

    public static int getWaitTime() {
        return WAIT_TIME;
    }

    public static int getMaxClients() {
        return MAX_CLIENTS;
    }

    public synchronized void start(Cars cars) throws InterruptedException {
        while (visitCount <= getMaxVisits() && !hasBuy) {
            System.out.println(name + ": " + " Зашел в автосалон " + visitCount);
            if (cars.getCars().isEmpty()) {
                System.out.println(name + ": " + " Машин нет");
            } else {
                hasBuy = true;
                car = cars.getCars().get(0);
                cars.getCars().remove(car);
                System.out.println(name + ": " + " уехал на машине " + car);
            }
            Thread.sleep(getWaitTime());
            visitCount++;
        }
        super.start();
    }
}
