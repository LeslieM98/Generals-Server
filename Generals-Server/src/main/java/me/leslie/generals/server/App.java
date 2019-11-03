package me.leslie.generals.server;


import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class App {
    private static me.leslie.generals.server.persistence.Database db;

    public static void main(String[] args) throws Exception {
        runJooqCodeGen();
    }

    public static void runJooqCodeGen() throws Exception {
        Configuration configuration = new Configuration()
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.extensions.ddl.DDLDatabase")
                                .withIncludes("ARMY | TROOP")
                                .withOutputSchemaToDefault(Boolean.TRUE)
                                .withProperties(new Property()
                                                .withKey("unqualifiedSchema")
                                                .withValue("none"),
                                        new Property()
                                                .withKey("scripts")
                                                .withValue("/db/schema.sql")))
                        .withGenerate(new Generate()
                                .withPojos(Boolean.TRUE)
                                .withDeprecationOnUnknownTypes(Boolean.FALSE)
                                .withImmutableInterfaces(Boolean.TRUE)
                                .withDaos(Boolean.TRUE))
                        .withTarget(new Target()
                                .withPackageName("me.leslie.generals.server.persistence.jooq")
                                .withDirectory("Generals-Server/src/main/java")));

        GenerationTool.generate(configuration);
    }
}
