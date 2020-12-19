

public class BodyPolicy implements PolicyProducer {
    public AutoInsurance getAutoObj() {
       return new BodyInjur();
    }
}
