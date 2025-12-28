import java.util.ArrayList;
import java.util.List;

public class DiningTableRepoImpl implements CrudRepository<DiningTable, Integer> {

    private List<DiningTable> tables = new ArrayList<>();

    @Override
    public void add(DiningTable table) {
        tables.add(table);
    }

    @Override
    public DiningTable getById(Integer id) {
        for (DiningTable t : tables) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @Override
    public List<DiningTable> getAll() {
        return tables;
    }

    @Override
    public void update(DiningTable table) {
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getId() == table.getId()) {
                tables.set(i, table);
                return;
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        tables.removeIf(t -> t.getId() == id);
    }
}