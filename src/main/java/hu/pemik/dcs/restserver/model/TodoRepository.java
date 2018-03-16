package hu.pemik.dcs.restserver.model;

import hu.pemik.dcs.restserver.database.Repository;
import hu.pemik.dcs.restserver.model.entity.Todo;

/**
 * @author pekmil
 */
public interface TodoRepository {

    void create(Todo entity);

    void edit(Todo entity);

    void remove(int id);

    Todo find(int id);

    Repository<Todo> findAll(String userName);
}
