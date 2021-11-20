# quarkus-todo Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

This is Todo app, and it was used to learn a little more  about Quarkus.

### Details
- DB: H2 as DB, with persistence to file
- Endpoint: /api/todos
- get(/api/todos): list
- get(/api/todos/{id}): get the todo with id={id}
- post(/api/todos): add new todo
- put(/api/todos/{id}): update todo with id={id}
- delete(/api/todos/{id}): delete todo with id={id}
- delete(/api/todos): delete all from DB


###Readers
```http request
### List
// @no-log
GET http://localhost:8080/api/todos

### get by id
// @no-log
GET http://localhost:8080/api/todos/a61773d5-f378-4162-a55b-b10cec7f9c95


```

###Modifiers
```http request
### create new
// @no-log
POST http://localhost:8080/api/todos
Content-Type: application/json

{
"title": "One Todo",
"content": "someday it will be done"
}


### update by id
// @no-log
PUT http://localhost:8080/api/todos/a61773d5-f378-4162-a55b-b10cec7f9c95
Content-Type: application/json

{
"id": "a61773d5-f378-4162-a55b-b10cec7f9c95",
"title": "z",
"content": "z"
}


### delete by id
// @no-log
DELETE http://localhost:8080/api/todos/a61773d5-f378-4162-a55b-b10cec7f9c95

### delete all
// @no-log
DELETE http://localhost:8080/api/todos

```


## Running the application in dev mode

To Run application in dev mode that enables live coding, execute:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

To create a native executable, execute:

```shell script
./mvnw package -Pnative
```

Or, if GraalVM not installed, to run the native executable build in a container, execute:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

Then it can be executed with native executable with: `./target/quarkus-todo-1.0.0-SNAPSHOT-runner`

Learn more about building native executables, visit https://quarkus.io/guides/maven-tooling.

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): Reactive implementation of JAX-RS with
additional features. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions
that depend on it.
- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
