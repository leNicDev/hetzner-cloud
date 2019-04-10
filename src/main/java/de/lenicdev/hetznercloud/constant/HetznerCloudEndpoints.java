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
    public static final String SERVER_POWER_ON = BASE_URL + "/servers/{serverId}/actions/poweron";
    public static final String SERVER_SOFT_REBOOT = BASE_URL + "/servers/{serverId}/actions/reboot";
    public static final String SERVER_RESET = BASE_URL + "/servers/{serverId}/actions/reset";
    public static final String SERVER_SHUTDOWN = BASE_URL + "/servers/{serverId}/actions/shutdown";
    public static final String SERVER_POWER_OFF = BASE_URL + "/servers/{serverId}/actions/poweroff";
    public static final String SERVER_RESET_ROOT_PASSWORD = BASE_URL + "/servers/{serverId}/actions/reset_password";
    public static final String SERVER_ENABLE_RESCUE_MODE = BASE_URL + "/servers/{serverId}/actions/enable_rescue";
    public static final String SERVER_DISABLE_RESCUE_MODE = BASE_URL + "/servers/{serverId}/actions/disable_rescue";
    public static final String SERVER_CREATE_IMAGE = BASE_URL + "/servers/{serverId}/actions/create_image";
    public static final String SERVER_REBUILD = BASE_URL + "/servers/{serverId}/actions/rebuild";
    public static final String SERVER_CHANGE_TYPE = BASE_URL + "/servers/{serverId}/actions/change_type";
    public static final String SERVER_ENABLE_BACKUP = BASE_URL + "/servers/{serverId}/actions/enable_backup";
    public static final String SERVER_DISABLE_BACKUP = BASE_URL + "/servers/{serverId}/actions/disable_backup";
    public static final String SERVER_ATTACH_ISO = BASE_URL + "/servers/{serverId}/actions/attach_iso";
    public static final String SERVER_DETACH_ISO = BASE_URL + "/servers/{serverId}/actions/detach_iso";
    public static final String SERVER_CHANGE_PROTECTION = BASE_URL + "/servers/{serverId}/actions/change_protection";
    public static final String SERVER_REQUEST_CONSOLE = BASE_URL + "/servers/{serverId}/actions/request_console";


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
