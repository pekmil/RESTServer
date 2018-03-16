package hu.pemik.dcs.restserver.service;

import hu.pemik.dcs.restserver.database.Repository;
import hu.pemik.dcs.restserver.model.entity.Todo;

/**
 * @author pekmil
 */
public interface TodoService {

    void create(Todo todo, String userName);

    void edit(Todo todo, String userName);

    void remove(int id);

    Todo find(int id);

    Repository<Todo> findAll(String userName);

}
