package com.theatreapp.greendaogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {

    public static final int DB_VERSION = 1000;
    public static final String OUTPUT_PACKAGE = "com.nathalieborodina.theatreapp";
    public static final String OUTPUT_FOLDER = "app/src/main/java";

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(DB_VERSION, OUTPUT_PACKAGE);

        new DaoGenerator().generateAll(schema, OUTPUT_FOLDER);

        addPerfomance(schema);
        addPerfomanceEvent(schema);

        new DaoGenerator().generateAll(schema, OUTPUT_FOLDER);
    }

    private static void addPerfomanceEvent(Schema schema) {
        Entity perfomanceEvent = schema.addEntity("PerfomanceEvent");
        perfomanceEvent.addIdProperty().autoincrement();
        perfomanceEvent.addLongProperty("eventId");
        perfomanceEvent.addDateProperty("date_time");
    }

    private static void addPerfomance(Schema schema) {
        Entity perfomance = schema.addEntity("Perfomance");
        perfomance.addIdProperty().autoincrement();
        perfomance.addLongProperty("eventId");
        perfomance.addStringProperty("title");
    }

}
