package hu.pemik.dcs.restserver.model;

import hu.pemik.dcs.restserver.model.entity.Todo;
import java.util.List;

/**
 *
 * @author pekmil
 */
public interface TodoRepository {

    void create(Todo entity);
    
    void edit(Todo entity);
    
    void remove(String id, String userName);
    
    Todo find(String id, String userName);
    
    List<Todo> findAll(String userName);
    
}
