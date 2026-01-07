package backend.repositories;

import backend.entities.OpeningHours;

public class OpeningHoursRepo extends BaseRepo<OpeningHours> {
    public OpeningHoursRepo(){
        super(OpeningHours.class);
    }
}
