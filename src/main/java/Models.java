import java.util.Arrays;
import java.util.List;

public class Models {
    private static final List<Model> models = Arrays.asList(
            new Model(Producers.getProducer(0), "Гранта"),
            new Model(Producers.getProducer(0), "Веста"),
            new Model(Producers.getProducer(0), "Ларгус"),
            new Model(Producers.getProducer(1), "Патриот"),
            new Model(Producers.getProducer(1), "Хантер"),
            new Model(Producers.getProducer(1), "Буханка"),
            new Model(Producers.getProducer(2), "Rio"),
            new Model(Producers.getProducer(2), "Ceed"),
            new Model(Producers.getProducer(2), "Soul"),
            new Model(Producers.getProducer(3), "Corolla"),
            new Model(Producers.getProducer(3), "Camry"),
            new Model(Producers.getProducer(3), "RAV4")
    );

    public static Model getModel(int index) {
        try {
            return models.get(index);
        } catch (Exception e) {
            return null;
        }

    }

    public static int getSize() {
        return models.size();
    }
}
