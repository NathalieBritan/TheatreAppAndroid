package com.theatreapp.greendaogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {

    public static final int DB_VERSION = 1000;
    public static final String OUTPUT_PACKAGE = "com.nathalieborodina.theatreapp.data.model";
    public static final String OUTPUT_FOLDER = "app/src/main/java";

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(DB_VERSION, OUTPUT_PACKAGE);

        new DaoGenerator().generateAll(schema, OUTPUT_FOLDER);

        addPerfomanceEvent(schema);
        addTickets(schema);

        new DaoGenerator().generateAll(schema, OUTPUT_FOLDER);
    }

    private static void addPerfomanceEvent(Schema schema) {
        Entity perfomanceEvent = schema.addEntity("PerfomanceEvent");
        perfomanceEvent.addIdProperty().autoincrement();
        perfomanceEvent.addLongProperty("eventId");
        perfomanceEvent.addDateProperty("date_time");
        perfomanceEvent.addStringProperty("title");
    }

    private static void addTickets(Schema schema) {
        Entity ticket = schema.addEntity("Ticket");
        ticket.addIdProperty().autoincrement();
        ticket.addLongProperty("ticketId");
        ticket.addLongProperty("eventId");
        ticket.addIntProperty("row");
        ticket.addIntProperty("seat");
        ticket.addLongProperty("customerId");
        ticket.addStringProperty("firstName");
        ticket.addStringProperty("lastName");
    }
}
