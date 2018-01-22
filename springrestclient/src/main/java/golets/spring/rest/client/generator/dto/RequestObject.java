package golets.spring.rest.client.generator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;


public class RequestObject {

    @JsonProperty("xtra")
    private XTRAData xtraData;

    @JsonProperty("meta")
    private MetaData metaData;

    @JsonProperty("events")
    private List<EventLevelData> events;

    @JsonProperty("date")
    private Date date;

    public RequestObject() {
    }

    public RequestObject(XTRAData xtraData, MetaData metaData, List<EventLevelData> events, Date date) {
        this.xtraData = xtraData;
        this.metaData = metaData;
        this.events = events;
        this.date = date;
    }

    public XTRAData getXtraData() {
        return xtraData;
    }

    public void setXtraData(XTRAData xtraData) {
        this.xtraData = xtraData;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<EventLevelData> getEvents() {
        return events;
    }

    public void setEvents(List<EventLevelData> events) {
        this.events = events;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
