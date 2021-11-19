package com.sarilhos.app.todo;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(RestHandler.class)
@Transactional
class HandlerTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void whenListTodo_thenShouldReturnCollection() {
        given().contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .body("size()", is(TestTodosRepository.size()));
    }

    @Test
    void whenGetTodo_thenShouldBeFound() {
        given().contentType(ContentType.JSON)
                .pathParam("id", TestTodosRepository.todo().getUuid())
                .when().get("/{id}")
                .then().statusCode(200)
                .body(is(TestTodosRepository.todoRestDtoAsJson()));
    }

    @Test
    void whenGetTodo_thenShouldBeNotFound() {
        given().contentType(ContentType.JSON)
                .pathParam("id", UUID.randomUUID().toString())
                .when().get("/{id}")
                .then().statusCode(404);
    }

    @Test
    void whenPostTodo_thenShouldSave() {
        TodoRestDto todoRestDto = TestTodosRepository.todoRestDto();
        todoRestDto.setId(null);
        given().contentType(ContentType.JSON)
                .body(TestTodosRepository.asJson(todoRestDto))
                .when().post()
                .then().statusCode(201);
    }

    @Test
    void whenPostTodo_thenShouldFailExists() {
        given().contentType(ContentType.JSON)
                .body(TestTodosRepository.todoRestDtoAsJson())
                .when().post()
                .then().statusCode(409);
    }

    @Test
    void whenPostTodo_thenShouldFailInvalidData() {
        TodoRestDto todoRestDto = TestTodosRepository.todoRestDto();
        todoRestDto.setTitle("");
        todoRestDto.setContent("");
        given().contentType(ContentType.JSON)
                .body(TestTodosRepository.todoRestDtoAsJson())
                .when().post()
                .then().statusCode(409);
    }

    @Test
    void whenPutTodo_thenShouldSave() {
        TodoRestDto todoRestDto = TestTodosRepository.todoRestDto();
        given().contentType(ContentType.JSON)
                .contentType("application/json")
                .body(TestTodosRepository.asJson(todoRestDto))
                .pathParam("id", todoRestDto.getId())
                .when().put("/{id}")
                .then().statusCode(204);
    }

    @Test
    void whenPutTodo_thenShouldFail404() {
        given().contentType(ContentType.JSON)
                .contentType("application/json")
                .body(TestTodosRepository.todoRestDtoAsJson())
                .pathParam("id", UUID.randomUUID())
                .when().put("/{id}")
                .then().statusCode(404);
    }

    @Test
    void whenDeleteTodo_thenShouldRemove() {
        given().contentType(ContentType.JSON)
                .pathParam("id", TestTodosRepository.todo().getUuid())
                .when().delete("/{id}")
                .then().statusCode(204);
    }

    @Test
    void whenDeleteTodo_thenShouldFail404() {
        given().contentType(ContentType.JSON)
                .pathParam("id", UUID.randomUUID())
                .when().delete("/{id}")
                .then().statusCode(404);
    }

    @Test
    void whenDeleteAll_thenShouldRemoveAll() {
        given().contentType(ContentType.JSON)
                .when().delete()
                .then().statusCode(204);
    }


}

