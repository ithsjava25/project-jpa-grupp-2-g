import java.util.ArrayList;
import java.util.List;

public class OpeningHoursRepoImpl implements CrudRepository<OpeningHours, Integer> {

    private List<OpeningHours> openingHoursList = new ArrayList<>();

    @Override
    public void add(OpeningHours openingHours) {
        openingHoursList.add(openingHours);
    }

    @Override
    public OpeningHours getById(Integer id) {
        for (OpeningHours oh : openingHoursList) {
            if (oh.getId() == id) {
                return oh;
            }
        }
        return null;
    }

    @Override
    public List<OpeningHours> getAll() {
        return openingHoursList;
    }

    @Override
    public void update(OpeningHours openingHours) {
        for (int i = 0; i < openingHoursList.size(); i++) {
            if (openingHoursList.get(i).getId() == openingHours.getId()) {
                openingHoursList.set(i, openingHours);
                return;
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        openingHoursList.removeIf(oh -> oh.getId() == id);
    }
}
