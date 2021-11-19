package com.sarilhos.app.todo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sarilhos.app.todo.RestHandler.fromDomain;


@Priority(1)
@Alternative
@RequestScoped
public class TestTodosRepository extends Repository {


    private static final Logger logger = Logger.getLogger(TestTodosRepository.class);

    private final static Set<Todo> testData = IntStream.range(1, 2)
            .mapToObj(Integer::toString)
            .map(i -> new Todo(UUID.randomUUID(), i, i)).collect(Collectors.toSet());


    public static String asJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String writeValueAsString = objectMapper.writeValueAsString(o);
            return StringEscapeUtils.unescapeJson(writeValueAsString);

        } catch (JsonProcessingException e) {
            logger.error(e);
            throw new RuntimeException("Fail to convert to json: ");
        }
    }

    public static int size() {
        return testData.size();
    }

    public static Stream<Todo> stream() {
        return testData.stream();
    }

    public static List<Todo> list() {
        return new ArrayList<>(testData);
    }

    public static Set<Todo> set() {
        return testData;
    }

    public static Todo todo() {
        return testData.stream().findFirst().orElseThrow();
    }

    public static String todoAsJson() {
        return asJson(testData.stream().findFirst().orElseThrow());
    }

    public static TodoRestDto todoRestDto() {
        return fromDomain(todo());
    }

    public static String todoRestDtoAsJson() {
        return asJson(fromDomain(todo()));
    }

    @PostConstruct
    public void init() {
        deleteAll();
        persist(testData.stream());
    }
}

