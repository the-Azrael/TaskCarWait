import java.util.*;

public class Main {
    private static final Cars cars = new Cars();

    public static void main(String[] args) throws InterruptedException {
        List<BuyerThread> bt = new ArrayList<>();
        for (int i = 1; i <= BuyerThread.getMaxClients(); i++) {
            bt.add(new BuyerThread("Покупатель" + i));
        }
        CarProduceThread cpt = new CarProduceThread("Поступление");
        cpt.start(cars);
        for (BuyerThread thread : bt) {
            thread.start(cars);
        }
    }

}
