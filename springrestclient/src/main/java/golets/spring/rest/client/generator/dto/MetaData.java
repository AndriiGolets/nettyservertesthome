package golets.spring.rest.client.generator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;
import java.util.UUID;


public class MetaData {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("gdid")
    private long gdid;

    @JsonProperty("ggid")
    private int ggid;

    @JsonProperty("ver")
    private String ver;

    @JsonProperty("client_time")
    private Date clientTime;

    @JsonProperty("client_time_local")
    private Date clientTimeLocal;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("anon_id")
    private String anonId;

    @JsonProperty("fed_access_token")
    private String fedAccessToken;

    @JsonProperty("install_id")
    private long installId;

    @JsonProperty("install_ts")
    private long installTs;

    @JsonProperty("data_center_id")
    private String dataCenterId;

    @JsonProperty("glot_rev")
    private String glotRev;

    @JsonProperty("proto_ver")
    private String protoVer;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("others")
    private Map<String, String> others;

    @Override
    public String toString() {
        return "MetaData{" +
                "uuid=" + uuid +
                ", gdid=" + gdid +
                ", ggid=" + ggid +
                ", ver='" + ver + '\'' +
                ", clientTime=" + clientTime +
                ", clientTimeLocal=" + clientTimeLocal +
                ", clientId='" + clientId + '\'' +
                ", anonId='" + anonId + '\'' +
                ", fedAccessToken='" + fedAccessToken + '\'' +
                ", installId=" + installId +
                ", installTs=" + installTs +
                ", dataCenterId='" + dataCenterId + '\'' +
                ", glotRev='" + glotRev + '\'' +
                ", protoVer='" + protoVer + '\'' +
                ", platform='" + platform + '\'' +
                ", others=" + others +
                '}';
    }

    public MetaData() {
    }

    public MetaData(UUID uuid, long gdid, int ggid, String ver, Date clientTime, Date clientTimeLocal, String clientId, String anonId, String fedAccessToken, long installId, long installTs, String dataCenterId, String glotRev, String protoVer, String platform, Map<String, String> others) {
        this.uuid = uuid;
        this.gdid = gdid;
        this.ggid = ggid;
        this.ver = ver;
        this.clientTime = clientTime;
        this.clientTimeLocal = clientTimeLocal;
        this.clientId = clientId;
        this.anonId = anonId;
        this.fedAccessToken = fedAccessToken;
        this.installId = installId;
        this.installTs = installTs;
        this.dataCenterId = dataCenterId;
        this.glotRev = glotRev;
        this.protoVer = protoVer;
        this.platform = platform;
        this.others = others;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public long getGdid() {
        return gdid;
    }

    public void setGdid(long gdid) {
        this.gdid = gdid;
    }

    public int getGgid() {
        return ggid;
    }

    public void setGgid(int ggid) {
        this.ggid = ggid;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAnonId() {
        return anonId;
    }

    public void setAnonId(String anonId) {
        this.anonId = anonId;
    }

    public String getFedAccessToken() {
        return fedAccessToken;
    }

    public void setFedAccessToken(String fedAccessToken) {
        this.fedAccessToken = fedAccessToken;
    }

    public long getInstallId() {
        return installId;
    }

    public void setInstallId(long installId) {
        this.installId = installId;
    }

    public long getInstallTs() {
        return installTs;
    }

    public void setInstallTs(long installTs) {
        this.installTs = installTs;
    }

    public String getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(String dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public String getGlotRev() {
        return glotRev;
    }

    public void setGlotRev(String glotRev) {
        this.glotRev = glotRev;
    }

    public String getProtoVer() {
        return protoVer;
    }

    public void setProtoVer(String protoVer) {
        this.protoVer = protoVer;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Map<String, String> getOthers() {
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }
}
