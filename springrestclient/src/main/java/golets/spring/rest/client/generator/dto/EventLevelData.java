package golets.spring.rest.client.generator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;
import java.util.UUID;


public class EventLevelData {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("client_time")
    private Date clientTime;

    @JsonProperty("client_time_local")
    private Date clientTimeLocal;

    @JsonProperty("token")
    private long token;

    @JsonProperty("eventid")
    private long eventId;

    @JsonProperty("gt")
    private double gt;

    @JsonProperty("ses_id")
    private long sesId;

    @JsonProperty("ses_t")
    private double sesT;

    @JsonProperty("ver")
    private String ver;

    @JsonProperty("connectivity")
    private long connectivity;

    @JsonProperty("event_data")
    private Map<String, String> eventData;

    @JsonProperty("others")
    private Map<String, String> others;

    @Override
    public String toString() {
        return "EventLevelData{" +
                "uuid=" + uuid +
                ", clientTime=" + clientTime +
                ", clientTimeLocal=" + clientTimeLocal +
                ", token=" + token +
                ", eventId=" + eventId +
                ", gt=" + gt +
                ", sesId=" + sesId +
                ", sesT=" + sesT +
                ", ver='" + ver + '\'' +
                ", connectivity=" + connectivity +
                ", eventData=" + eventData +
                ", others=" + others +
                '}';
    }

    public EventLevelData() {
    }

    public EventLevelData(UUID uuid, Date clientTime, Date clientTimeLocal, long token, long eventId, double gt, long sesId, double sesT, String ver, long connectivity, Map<String, String> eventData, Map<String, String> others) {
        this.uuid = uuid;
        this.clientTime = clientTime;
        this.clientTimeLocal = clientTimeLocal;
        this.token = token;
        this.eventId = eventId;
        this.gt = gt;
        this.sesId = sesId;
        this.sesT = sesT;
        this.ver = ver;
        this.connectivity = connectivity;
        this.eventData = eventData;
        this.others = others;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getClientTime() {
        return clientTime;
    }

    public void setClientTime(Date clientTime) {
        this.clientTime = clientTime;
    }

    public Date getClientTimeLocal() {
        return clientTimeLocal;
    }

    public void setClientTimeLocal(Date clientTimeLocal) {
        this.clientTimeLocal = clientTimeLocal;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public double getGt() {
        return gt;
    }

    public void setGt(double gt) {
        this.gt = gt;
    }

    public long getSesId() {
        return sesId;
    }

    public void setSesId(long sesId) {
        this.sesId = sesId;
    }

    public double getSesT() {
        return sesT;
    }

    public void setSesT(double sesT) {
        this.sesT = sesT;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public long getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(long connectivity) {
        this.connectivity = connectivity;
    }

    public Map<String, String> getEventData() {
        return eventData;
    }

    public void setEventData(Map<String, String> eventData) {
        this.eventData = eventData;
    }

    public Map<String, String> getOthers() {
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }
}
