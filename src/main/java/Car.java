import java.time.LocalDate;

public class Car {
    private Model model;
    private LocalDate produceDate;

    public Car(Model model, LocalDate produceDate) {
        this.model = model;
        this.produceDate = produceDate;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public String toString() {
        return this.getModel().getProducer().getName() + " " + this.getModel().getName();
    }

    public LocalDate getProduceDate() {
        return produceDate;
    }
}
