package hu.pemik.dcs.restserver;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        // Bind implementations to interfaces here.
        // bind(DatabaseTodoRepository.class).to(TodoRepositoryInterface.class).in(Singleton.class);
    }

}
