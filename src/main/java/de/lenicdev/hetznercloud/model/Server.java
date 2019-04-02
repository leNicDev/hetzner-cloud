package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Server {

    private Long id;
    private String name;
    private String status;
    private Date created;
    @JsonProperty("server_type")
    private ServerType serverType;
    private Datacenter datacenter;
    private Image image;
    private Iso iso;
    @JsonProperty("rescue_enabled")
    private Boolean rescueEnabled;
    private Boolean locked;
    @JsonProperty("backup_window")
    private String backupWindow;
    @JsonProperty("outgoing_traffic")
    private Long outgoingTraffic;
    @JsonProperty("ingoing_traffic")
    private Long ingoingTraffic;
    @JsonProperty("included_traffic")
    private Long includedTraffic;
    private Protection protection;
    private Map<String, String> labels;
    private List<Long> volumes;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public Datacenter getDatacenter() {
        return datacenter;
    }

    public Image getImage() {
        return image;
    }

    public Iso getIso() {
        return iso;
    }

    public Boolean getRescueEnabled() {
        return rescueEnabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public String getBackupWindow() {
        return backupWindow;
    }

    public Long getOutgoingTraffic() {
        return outgoingTraffic;
    }

    public Long getIngoingTraffic() {
        return ingoingTraffic;
    }

    public Long getIncludedTraffic() {
        return includedTraffic;
    }

    public Protection getProtection() {
        return protection;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public List<Long> getVolumes() {
        return volumes;
    }

}
