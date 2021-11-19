package com.sarilhos.app.todo;

import com.sarilhos.app.exceptions.InvalidRequest;
import com.sarilhos.app.exceptions.NotFound;
import com.sarilhos.app.exceptions.TodoException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class ServiceTest {


    @Inject
    IService service;

    @InjectMock
    Repository repoMock;


    @BeforeEach
    void setUp() {
        Mockito.reset(repoMock);
    }

    @Test
    void list() {
        Mockito.when(repoMock.listAll()).thenReturn(TestTodosRepository.list());
        assertEquals(service.list(), TestTodosRepository.set());
    }

    @Test
    void get() throws TodoException {
        Todo todo = TestTodosRepository.todo();
        Mockito.when(repoMock.findByUUID(any())).thenReturn(Optional.ofNullable(todo));
        assertEquals(service.get(any()), todo);

        Mockito.when(repoMock.findByUUID(any())).thenReturn(Optional.empty());
        assertThrows(NotFound.class, () -> service.get(any()));
    }

    @Test
    void add() throws TodoException {
        Todo todo = TestTodosRepository.todo();
        Mockito.when(repoMock.findByUUID(any())).thenReturn(Optional.empty(), Optional.of(todo));
        assertEquals(service.add(todo), todo);
        verify(repoMock, times(1)).persistAndFlush(todo);


        reset(repoMock);
        Todo mock = mock(Todo.class);
        Mockito.when(mock.isValid()).thenReturn(true, false);
        Mockito.when(repoMock.findByUUID(any())).thenReturn(Optional.empty(), Optional.of(mock));
        assertDoesNotThrow(() -> service.add(mock));
        verify(repoMock, times(1)).persistAndFlush(mock);
        assertThrows(InvalidRequest.class, () -> service.add(mock));

    }


    @Test
    void update() {
        Todo todoMock = mock(Todo.class);

        when(repoMock.findByUUID(any())).thenReturn(Optional.of(todoMock));
        when(todoMock.isValid()).thenReturn(true);
        assertDoesNotThrow(() -> service.update(todoMock));
        verify(repoMock, times(1)).persistAndFlush(any());

        reset(repoMock);
        when(repoMock.findByUUID(any())).thenReturn(Optional.empty());
        when(todoMock.isValid()).thenReturn(true);
        assertThrows(NotFound.class, () -> service.update(todoMock));
        verify(repoMock, times(0)).persistAndFlush(any());


        // valid true
        reset(repoMock);
        when(repoMock.findByUUID(any())).thenReturn(Optional.of(todoMock));
        when(todoMock.isValid()).thenReturn(true);
        assertDoesNotThrow(() -> service.update(todoMock));
        verify(repoMock, times(1)).persistAndFlush(any());

        // is valid false
        Mockito.reset(repoMock);
        Mockito.when(repoMock.findByUUID(any())).thenReturn(Optional.of(todoMock));
        Mockito.when(todoMock.isValid()).thenReturn(false);
        assertThrows(InvalidRequest.class, () -> service.update(todoMock));
        verify(repoMock, times(0)).persistAndFlush(any());

    }

    @Test
    void delete() {
        when(repoMock.findByUUID(any())).thenReturn(Optional.of(mock(Todo.class)));
        assertDoesNotThrow(() -> service.delete(any()));
        verify(repoMock, times(1)).deleteByUUID(any());

        Mockito.reset(repoMock);
        when(repoMock.findByUUID(any())).thenReturn(Optional.empty());
        assertThrows(NotFound.class, () -> service.delete(any()));
        verify(repoMock, times(0)).deleteByUUID(any());
    }


    @Test
    void deleteAll() {
        assertDoesNotThrow(() -> service.delete());
        verify(repoMock, times(1)).deleteAll();
    }

}

