package hu.pemik.dcs.restserver.service;

import hu.pemik.dcs.restserver.model.entity.Todo;

import java.util.List;

/**
 * @author pekmil
 */
public interface TodoService {

    void create(Todo todo, String userName);

    void edit(Todo todo, String userName);

    void remove(String id, String userName);

    Todo find(String id, String userName);

    List<Todo> findAll(String userName);

}
