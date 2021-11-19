package com.sarilhos.app.todo;

import com.sarilhos.app.exceptions.TodoException;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;

public interface IService {

    Stream<Todo> stream();

    Collection<Todo> list();

    Todo get(UUID id) throws TodoException;

    Todo add(Todo todo) throws TodoException;

    void update(Todo todo) throws TodoException;

    void delete();

    void delete(UUID id) throws TodoException;

}
