package com.sarilhos.app;


import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.h2.tools.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.sql.SQLException;

@ApplicationScoped
public class ApplicationLifecycle {
    Server server;

    void onStart(@Observes StartupEvent event) throws SQLException {
        //server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    void onStop(@Observes ShutdownEvent event) {
        //  server.stop();
    }
}