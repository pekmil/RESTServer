package hu.pemik.dcs.restserver.model;

import hu.pemik.dcs.restserver.database.Database;
import hu.pemik.dcs.restserver.database.Repository;
import hu.pemik.dcs.restserver.model.entity.Todo;

public class DatabaseTodoRepository implements TodoRepository {

    private Repository<Todo> todos;

    public DatabaseTodoRepository() {
        this.todos = Database.query().todos;

        Todo todo = new Todo();
        todo.setId(this.todos.count() + 1);
        todo.setUserName("tbence");
        todo.setName("Test todo");
        todo.setDescription("Test todo description");

        try {
            this.todos.insert(todo);
        } catch (Exception e) {
            System.out.println("Insert failed: " + e.getMessage());
        }

    }

    @Override
    public void create(Todo entity) {
        entity.setId(this.todos.count() + 1);

        try {
            this.todos.insert(entity);
            Database.query().save();
        } catch (Exception e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    @Override
    public void edit(Todo entity) {
        checkTodo(entity);
        // TODO: editing...
    }

    @Override
    public void remove(int id) {
        Todo todo = this.todos.find(id);
        if (todo == null) {
            throw new IllegalArgumentException("Todo with id: " + id + " doesn't exists!");
        }

        this.todos.delete(id);
    }

    @Override
    public Todo find(int id) {
        return this.todos.find(id);
    }

    @Override
    public Repository<Todo> findAll(String userName) {
        return this.todos.where("userName", userName);
    }

    private void checkTodo(Todo entity) {
        if (!this.todos.where("id", entity.getId()).exists()) {
            throw new IllegalArgumentException("Todo with id: " + entity.getId() + " doesn't exists!");
        }
    }

}
