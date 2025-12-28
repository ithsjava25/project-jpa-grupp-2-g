import java.util.List;

public interface CrudRepository<T, ID> {

    void add(T entity);

    T getById(ID id);

    List<T> getAll();

    void update(T entity);

    void deleteById(ID id);
}
