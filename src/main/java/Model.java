public class Model {
    private Producer producer;
    private String name;

    public Model(Producer producer, String name) {
        this.producer = producer;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Producer getProducer() {
        return producer;
    }
}
