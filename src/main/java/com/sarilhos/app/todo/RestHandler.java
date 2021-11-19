package com.sarilhos.app.todo;

import com.sarilhos.app.exceptions.TodoException;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Path("/api/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestHandler {


    @Inject
    IService useCases;

    public static TodoRestDto fromDomain(Todo todo) {
        return new TodoRestDto(todo.getUuid().toString(), todo.getTitle(), todo.getContent());
    }

    @GET
    @Transactional
    public RestResponse<Set<TodoRestDto>> list() {
        Set<TodoRestDto> dtos = useCases.stream()
                .map(RestHandler::fromDomain)
                .collect(Collectors.toSet());
        return RestResponse.ok(dtos);
    }

    @Path("{id}")
    @GET
    @Transactional
    public RestResponse<TodoRestDto> get(@PathParam("id") String id) throws TodoException {
        Todo todo = useCases.get(UUID.fromString(id));
        return RestResponse.ok(fromDomain(todo));
    }

    @POST
    @Transactional
    public RestResponse<Void> post(TodoRestDto dto) throws TodoException {
        Todo todo = useCases.add(toDomain(dto));
        return RestResponse.created(URI.create(todo.getUuid().toString()));
    }

    @Path("{id}")
    @PUT
    @Transactional
    public RestResponse<Void> put(@PathParam("id") String id, TodoRestDto dto) throws TodoException {
        dto.setId(id);
        useCases.update(toDomain(dto));
        return RestResponse.noContent();
    }

    @Path("{id}")
    @DELETE
    @Transactional
    public RestResponse<java.lang.Void> delete(@PathParam("id") String id) throws TodoException {
        useCases.delete(UUID.fromString(id));
        return RestResponse.noContent();
    }

    @DELETE
    @Transactional
    public RestResponse<java.lang.Void> delete() {
        useCases.delete();
        return RestResponse.noContent();
    }

    private Todo toDomain(TodoRestDto dto) {
        if (dto.getId() == null)
            return new Todo(dto.getTitle(), dto.getContent());
        return new Todo(UUID.fromString(dto.getId()), dto.getTitle(), dto.getContent());
    }
}
