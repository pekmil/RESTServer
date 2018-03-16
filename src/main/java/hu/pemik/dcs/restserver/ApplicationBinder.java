package hu.pemik.dcs.restserver;

import hu.pemik.dcs.restserver.model.DatabaseTodoRepository;
import hu.pemik.dcs.restserver.model.TodoRepository;
import hu.pemik.dcs.restserver.service.DefaultTodoService;
import hu.pemik.dcs.restserver.service.TodoService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(DatabaseTodoRepository.class).to(TodoRepository.class).in(Singleton.class);
        bind(DefaultTodoService.class).to(TodoService.class).in(Singleton.class);
    }

}
