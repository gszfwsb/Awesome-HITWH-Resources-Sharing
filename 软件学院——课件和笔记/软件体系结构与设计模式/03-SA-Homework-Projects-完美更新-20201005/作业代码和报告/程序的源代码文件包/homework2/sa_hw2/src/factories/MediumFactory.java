package factories;

import condos.*;
import houses.*;
import semidetachers.*;

public class MediumFactory extends BuildingFactory {

    public House getHouse() {
        return new MedHouse();
    }

    public Condo getCondo() {
        return new MedCondo();
    }

    @Override
    public SemiDetacher getSemiDetacher() {
        return new MedSemiDe();
    }
}
