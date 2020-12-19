

public class ComPolicy implements PolicyProducer {

    public AutoInsurance getAutoObj() {
        return new ComCover();
    }
}
