package library.persistence.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface DaoInterface<T> {

    T create(T t);
    T read(int id);
    T update (T t);
    void delete(int id);
    List<T> readAll();

}
