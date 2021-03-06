package com.nathalieborodina.theatreapp.data.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PERFOMANCE_EVENT".
 */
public class PerfomanceEvent {

    private Long id;
    private Long eventId;
    private java.util.Date date_time;
    private String title;

    public PerfomanceEvent() {
    }

    public PerfomanceEvent(Long id) {
        this.id = id;
    }

    public PerfomanceEvent(Long id, Long eventId, java.util.Date date_time, String title) {
        this.id = id;
        this.eventId = eventId;
        this.date_time = date_time;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public java.util.Date getDate_time() {
        return date_time;
    }

    public void setDate_time(java.util.Date date_time) {
        this.date_time = date_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
