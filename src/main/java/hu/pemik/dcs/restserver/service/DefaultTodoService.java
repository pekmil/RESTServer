package hu.pemik.dcs.restserver.service;

import hu.pemik.dcs.restserver.database.Repository;
import hu.pemik.dcs.restserver.model.TodoRepository;
import hu.pemik.dcs.restserver.model.entity.Todo;

import javax.inject.Inject;

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
    public void remove(int id) {
        this.repository.remove(id);
    }

    @Override
    public Todo find(int id) {
        return this.repository.find(id);
    }

    @Override
    public Repository<Todo> findAll(String userName) {
        return this.repository.findAll(userName);
    }

    private void checkUserName(Todo todo, String userName) {
        if (!todo.getUserName().equals(userName)) {
            throw new IllegalStateException("Username: " + userName + " doesn't exists!");
        }
    }

}
