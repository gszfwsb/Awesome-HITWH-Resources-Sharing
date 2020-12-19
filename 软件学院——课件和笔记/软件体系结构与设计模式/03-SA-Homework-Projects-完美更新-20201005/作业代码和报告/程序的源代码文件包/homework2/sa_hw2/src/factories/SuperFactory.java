package factories;

import condos.*;
import houses.*;
import semidetachers.*;

public class SuperFactory extends BuildingFactory {

    public House getHouse() {
        return new SupHouse();
    }

    public Condo getCondo() {
        return new SupCondo();
    }


    @Override
    public SemiDetacher getSemiDetacher() {
        return new SupSemiDe();
    }
}
