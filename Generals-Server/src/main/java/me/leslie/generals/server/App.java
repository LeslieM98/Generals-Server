package me.leslie.generals.server;

import java.sql.DriverManager;

import com.github.simplenet.Server;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generate;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Property;
import org.jooq.meta.jaxb.Target;

public class App {
        private static me.leslie.generals.server.persistence.Database db;

        public static void main(String[] args) throws Exception {
                db = me.leslie.generals.server.persistence.Database.get();
                runJooqCodeGen();
                Server server = new Server();
                server.bind("localhost", 8080);
                System.out.println("Server launched on localhost:8080");
                server.onConnect(x -> System.out.println("Client connected"));

        }

        public static void runJooqCodeGen() throws Exception {
                String url = db.getUrl();
                String driver = DriverManager.getDriver(url).getClass().getName();
                Configuration configuration = new Configuration().withJdbc(new Jdbc().withDriver(driver).withUrl(url))
                                .withGenerator(new Generator()
                                                .withDatabase(new Database().withIncludes("TROOP")
                                                                .withProperties(new Property().withKey("scripts")
                                                                                .withValue("src/main/resources/db/")))
                                                .withGenerate(new Generate().withPojos(Boolean.TRUE)
                                                                .withDeprecationOnUnknownTypes(Boolean.FALSE))
                                                .withTarget(new Target().withPackageName(
                                                                "me.leslie.generals.server.persistence.jooq")
                                                                .withDirectory("Generals-Server/src/main/java")));

                GenerationTool.generate(configuration);
        }
}
