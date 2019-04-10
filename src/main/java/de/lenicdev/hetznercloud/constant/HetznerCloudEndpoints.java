package de.lenicdev.hetznercloud.constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class HetznerCloudEndpoints {

    private static final String BASE_URL = "https://api.hetzner.cloud/v1";

    public static final String GET_ALL_SERVER_TYPES = BASE_URL + "/servers";
    public static final String CREATE_SERVER = BASE_URL + "/servers";
    public static final String GET_ALL_SERVERS = BASE_URL + "/servers";
    public static final String GET_SERVER = BASE_URL + "/servers/";
    public static final String UPDATE_SERVER = BASE_URL + "/servers/";
    public static final String SERVER_METRICS = BASE_URL + "/servers/{serverId}/metrics?type={type}&start={start}&end={end}";
    public static final String SERVER_ACTIONS = BASE_URL + "/servers/{serverId}/actions";
    public static final String SERVER_ACTION = BASE_URL + "/servers/{serverId}/actions/{actionId}";


    private HetznerCloudEndpoints() {}


    public static String getMetricsUrl(String serverId, String type, Date start, Date end) throws UnsupportedEncodingException {
        final String encodedServerId = URLEncoder.encode(serverId, "UTF-8");
        final String encodedType = URLEncoder.encode(type, "UTF-8");
        final String encodedStart = URLEncoder.encode(start.toString(), "UTF-8");
        final String encodedEnd = URLEncoder.encode(end.toString(), "UTF-8");

        return SERVER_METRICS
                .replace("{serverId}", encodedServerId)
                .replace("{type}", encodedType)
                .replace("{start}", encodedStart)
                .replace("{end}", encodedEnd);
    }

}
