import java.time.LocalDate;

public class Car {
    private Model model;

    public Car(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public String toString() {
        return this.getModel().getProducer().getName() + " " + this.getModel().getName();
    }

}
