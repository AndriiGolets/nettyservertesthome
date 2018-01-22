package golets.spring.rest.client.generator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Date;
import java.util.Map;
import java.util.UUID;


public class XTRAData {

    @JsonProperty("gdid")
    private long gdid;

    @JsonProperty("fed_id")
    private UUID fedId;

    @JsonProperty("server_time")
    private Date serverTime;

    @JsonProperty("client_ip")
    private String clientIp;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("glot_version")
    private String glotVersion;

    @JsonProperty("glot_source")
    private String glotSource;

    @JsonProperty("others")
    private Map<String, String> others;

    @Override
    public String toString() {
        return "XTRAData{" +
                "gdid=" + gdid +
                ", fedId=" + fedId +
                ", serverTime=" + serverTime +
                ", clientIp='" + clientIp + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", glotVersion='" + glotVersion + '\'' +
                ", glotSource='" + glotSource + '\'' +
                ", others=" + others +
                '}';
    }

    public XTRAData() {
    }

    public XTRAData(long gdid, UUID fedId, Date serverTime, String clientIp, String countryCode, String cityName, String glotVersion, String glotSource, Map<String, String> others) {
        this.gdid = gdid;
        this.fedId = fedId;
        this.serverTime = serverTime;
        this.clientIp = clientIp;
        this.countryCode = countryCode;
        this.cityName = cityName;
        this.glotVersion = glotVersion;
        this.glotSource = glotSource;
        this.others = others;
    }

    public long getGdid() {
        return gdid;
    }

    public void setGdid(long gdid) {
        this.gdid = gdid;
    }

    public UUID getFedId() {
        return fedId;
    }

    public void setFedId(UUID fedId) {
        this.fedId = fedId;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getGlotVersion() {
        return glotVersion;
    }

    public void setGlotVersion(String glotVersion) {
        this.glotVersion = glotVersion;
    }

    public String getGlotSource() {
        return glotSource;
    }

    public void setGlotSource(String glotSource) {
        this.glotSource = glotSource;
    }

    public Map<String, String> getOthers() {
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }
}
