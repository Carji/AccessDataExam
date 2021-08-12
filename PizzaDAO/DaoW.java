package PizzaDAO;

public interface DaoW<T> {

    void insert(T t);
    void update(T t);
    void delete(T t);

}
