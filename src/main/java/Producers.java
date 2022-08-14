import java.util.Arrays;
import java.util.List;

public class Producers {
    private static final List<Producer> producers = Arrays.asList(
            new Producer("ВАЗ"),
            new Producer("УАЗ"),
            new Producer("Kia"),
            new Producer("Toyota")
    );

    public static Producer getProducer(int index) {
        try {
            return producers.get(index);
        } catch (Exception e) {
            return null;
        }
    }
}
