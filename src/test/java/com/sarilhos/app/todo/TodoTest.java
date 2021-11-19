package com.sarilhos.app.todo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@QuarkusTest
class TodoTest {

    @Test
    void isValid() {
        assertFalse(new Todo("", "").isValid());
        assertFalse(new Todo(null, "").isValid());
        assertFalse(new Todo("", null).isValid());
        assertFalse(new Todo(null, null).isValid());

        assertTrue(new Todo("todo", "").isValid());
        assertTrue(new Todo("", "todo").isValid());
    }
}