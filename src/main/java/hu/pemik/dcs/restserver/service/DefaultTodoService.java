package hu.pemik.dcs.restserver.service;

import hu.pemik.dcs.restserver.model.TodoRepository;
import hu.pemik.dcs.restserver.model.entity.Todo;

import javax.inject.Inject;
import java.util.List;

/**
 * @author pekmil
 */
public class DefaultTodoService implements TodoService {

    @Inject
    private TodoRepository repository;

    @Override
    public void create(Todo todo, String userName) {
        todo.setUserName(userName);
        this.repository.create(todo);
    }

    @Override
    public void edit(Todo todo, String userName) {
        checkUserName(todo, userName);
        this.repository.edit(todo);
    }

    @Override
    public void remove(String id, String userName) {
        this.repository.remove(id, userName);
    }

    @Override
    public Todo find(String id, String userName) {
        return this.repository.find(id, userName);
    }

    @Override
    public List<Todo> findAll(String userName) {
        return this.repository.findAll(userName);
    }

    private void checkUserName(Todo todo, String userName) {
        if (!todo.getUserName().equals(userName)) {
            throw new IllegalStateException("Username: " + userName + " doesn't exists!");
        }
    }

}
