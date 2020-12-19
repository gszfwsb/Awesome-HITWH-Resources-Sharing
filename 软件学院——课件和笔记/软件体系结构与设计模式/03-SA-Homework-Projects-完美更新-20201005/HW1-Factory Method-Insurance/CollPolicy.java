


public class CollPolicy implements PolicyProducer {
    public AutoInsurance getAutoObj() {
        return new Collision();
    }
}
