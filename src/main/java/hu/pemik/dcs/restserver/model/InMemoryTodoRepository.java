package hu.pemik.dcs.restserver.model;

import hu.pemik.dcs.restserver.model.entity.Todo;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author pekmil
 */
public class InMemoryTodoRepository implements TodoRepository {
    
    private final Map<String, List<Todo>> todos;

    public InMemoryTodoRepository() {
        this.todos = Collections.synchronizedMap(new HashMap<>());
        List<Todo> t = new ArrayList<>();
        Todo todo = new Todo(); 
        todo.setId(UUID.randomUUID().toString()); todo.setUserName("pekmil"); 
        todo.setName("Test todo"); todo.setDescription("Test todo description"); 
        t.add(todo);
        this.todos.put("pekmil", t);
        
    }

    @Override
    public void create(Todo entity) {
        entity.setId(UUID.randomUUID().toString());
        if(!this.todos.containsKey(entity.getUserName())){
            this.todos.put(entity.getUserName(), new ArrayList<>());
        }
        this.todos.get(entity.getUserName()).add(entity);
    }

    @Override
    public void edit(Todo entity) {
        checkUserName(entity.getUserName());
        checkTodo(entity);
        List<Todo> t = this.todos.get(entity.getUserName());
        t.set(t.indexOf(entity), entity);
    }

    @Override
    public void remove(String id, String userName) {
        checkUserName(userName);
        Optional<Todo> todo = this.todos.get(userName).stream()
            .filter(t -> t.getUserName().equals(userName) && t.getId().equals(id))
            .findFirst();
        if(todo.isPresent()){
            this.todos.get(userName).remove(todo.get());
        }
        else{
            throw new IllegalArgumentException("Todo with id: " + id + " doesn't exists!");
        }
    }

    @Override
    public Todo find(String id, String userName) {
        checkUserName(userName);
        Optional<Todo> todo = this.todos.get(userName).stream().filter(t -> t.getUserName().equals(userName)).findFirst();
        return todo.orElse(null);
    }

    @Override
    public List<Todo> findAll(String userName) {
        checkUserName(userName);
        return this.todos.get(userName);
    }

    private void checkUserName(String userName){
        if(!this.todos.containsKey(userName)){
            throw new IllegalStateException("Username: " + userName + " doesn't exists!");
        }
    }
    
    private void checkTodo(Todo entity){
         if(!this.todos.get(entity.getUserName()).contains(entity)){
            throw new IllegalArgumentException("Todo with id: " + entity.getId() + " doesn't exists!");
        }
    }

}
