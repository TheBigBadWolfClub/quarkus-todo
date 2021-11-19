package com.sarilhos.app.todo;

import com.sarilhos.app.exceptions.InvalidRequest;
import com.sarilhos.app.exceptions.NotFound;
import com.sarilhos.app.exceptions.TodoException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Stream;

@ApplicationScoped
public class Service implements IService {


    @Inject
    Repository repo;


    public Stream<Todo> stream() {
        return repo.streamAll();
    }

    @Override
    public Collection<Todo> list() {
        return new HashSet<>(repo.listAll());
    }

    @Override
    public Todo add(Todo todo) throws TodoException {
        if (!todo.isValid() || repo.findByUUID(todo.getUuid()).isPresent()) throw new InvalidRequest();
        repo.persistAndFlush(todo);
        return repo.findByUUID(todo.getUuid()).orElseThrow(NotFound::new);
    }

    @Override
    public void update(Todo todo) throws TodoException {
        if (!todo.isValid()) throw new InvalidRequest();
        Todo fromDb = repo.findByUUID(todo.getUuid()).orElseThrow(NotFound::new);
        fromDb.setTitle(todo.getTitle());
        fromDb.setContent(todo.getContent());
        repo.persistAndFlush(fromDb);
    }

    @Override
    public void delete() {
        repo.deleteAll();
    }

    @Override
    public void delete(UUID id) throws TodoException {
        repo.findByUUID(id).orElseThrow(NotFound::new);
        repo.deleteByUUID(id);

    }

    @Override
    public Todo get(UUID id) throws TodoException {
        return repo.findByUUID(id).orElseThrow(NotFound::new);
    }
}
