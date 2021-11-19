package com.sarilhos.app.todo;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "todos")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Todo extends PanacheEntityBase {

    @Id
    private UUID uuid;

    private String title;

    private String content;

    public Todo(String title, String content) {
        this(UUID.randomUUID(), title, content);
    }

    public boolean isValid() {
        return title != null && content != null
                && !(title.equals("") && content.equals(""));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Todo todo = (Todo) o;
        return uuid != null && Objects.equals(uuid, todo.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
