package de.lenicdev.hetznercloud.model.request.metrics;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class GetCpuMetricsRequest extends MetricsRequest {

    public GetCpuMetricsRequest(String serverId, String type, Date start, Date end) throws UnsupportedEncodingException {
        super(HetznerCloudEndpoints.getMetricsUrl(serverId, type, start, end),
                serverId, type, start, end);
    }

}
