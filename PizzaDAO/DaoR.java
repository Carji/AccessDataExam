package PizzaDAO;

import java.util.UUID;

public interface DaoR<T> {
    
    public T getAll();
    public T get(UUID id);
}
