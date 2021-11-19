package com.sarilhos.app.todo;


import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class Repository implements PanacheRepository<Todo> {

    Optional<Todo> findByUUID(UUID uuid) {
        return find("uuid", uuid).singleResultOptional();
    }

    void deleteByUUID(UUID uuid) {
        delete("uuid", uuid);
    }

}
