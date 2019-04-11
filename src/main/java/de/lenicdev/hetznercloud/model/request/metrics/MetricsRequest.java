package de.lenicdev.hetznercloud.model.request.metrics;

import de.lenicdev.hetznercloud.model.request.HetznerCloudRequest;

import java.util.Date;

public abstract class MetricsRequest extends HetznerCloudRequest {

    private String serverId;
    private String type;
    private Date start;
    private Date end;


    public MetricsRequest(String url) {
        super(url);
    }

    public MetricsRequest(String url, String serverId, String type, Date start, Date end) {
        super(url);
        this.serverId = serverId;
        this.type = type;
        this.start = start;
        this.end = end;
    }


    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
