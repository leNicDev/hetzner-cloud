package de.lenicdev.hetznercloud.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Configuration of all URLs for the {@link de.lenicdev.hetznercloud.HetznerCloudClient}
 */
public class HetznerCloudClientUrlConfig {

    private String baseUrl = "http://localhost:8090/v1";

    private String getAllServerTypes = "/servers";
    private String createServer = "/servers";
    private String getAllServers = "/servers";
    private String getServer = "/servers/{serverId}";
    private String updateServer = "/servers/{serverId}";
    private String serverMetrics = "/servers/{serverId}/metrics?type={type}&start={start}&end={end}";
    private String serverActions = "/servers/{serverId}/actions";
    private String serverAction = "/servers/{serverId}/actions/{actionId}";
    private String serverPowerOn = "/servers/{serverId}/actions/poweron";
    private String serverSoftReboot = "/servers/{serverId}/actions/reboot";
    private String serverReset = "/servers/{serverId}/actions/reset";
    private String serverShutdown = "/servers/{serverId}/actions/shutdown";
    private String serverPowerOff = "/servers/{serverId}/actions/poweroff";
    private String serverResetRootPassword = "/servers/{serverId}/actions/reset_password";
    private String serverEnableRescueMode = "/servers/{serverId}/actions/enable_rescue";
    private String serverDisableRescueMode = "/servers/{serverId}/actions/disable_rescue";
    private String serverCreateImage = "/servers/{serverId}/actions/create_image";
    private String serverRebuild = "/servers/{serverId}/actions/rebuild";
    private String serverChangeType = "/servers/{serverId}/actions/change_type";
    private String serverEnableBackup = "/servers/{serverId}/actions/enable_backup";
    private String serverDisableBackup = "/servers/{serverId}/actions/disable_backup";
    private String serverAttachIso = "/servers/{serverId}/actions/attach_iso";
    private String serverDetachIso = "/servers/{serverId}/actions/detach_iso";
    private String serverChangeProtection = "/servers/{serverId}/actions/change_protection";
    private String serverRequestConsole = "/servers/{serverId}/actions/request_console";


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getGetAllServerTypes() {
        return baseUrl + getAllServerTypes;
    }

    public void setGetAllServerTypes(String getAllServerTypes) {
        this.getAllServerTypes = getAllServerTypes;
    }

    public String getCreateServer() {
        return baseUrl + createServer;
    }

    public void setCreateServer(String createServer) {
        this.createServer = createServer;
    }

    public String getGetAllServers() {
        return baseUrl + getAllServers;
    }

    public void setGetAllServers(String getAllServers) {
        this.getAllServers = getAllServers;
    }

    public String getGetServer(String serverId) {
        return baseUrl + getServer.replace("{serverId}", serverId);
    }

    public void setGetServer(String getServer) {
        this.getServer = getServer;
    }

    public String getUpdateServer(String serverId) {
        return baseUrl + updateServer.replace("{serverId}", serverId);
    }

    public void setUpdateServer(String updateServer) {
        this.updateServer = updateServer;
    }

    public String getServerMetrics(String serverId, String type, Date start, Date end) throws UnsupportedEncodingException {
        final String encodedServerId = URLEncoder.encode(serverId, "UTF-8");
        final String encodedType = URLEncoder.encode(type, "UTF-8");
        final String encodedStart = URLEncoder.encode(start.toString(), "UTF-8");
        final String encodedEnd = URLEncoder.encode(end.toString(), "UTF-8");

        return baseUrl + serverMetrics
                .replace("{serverId}", encodedServerId)
                .replace("{type}", encodedType)
                .replace("{start}", encodedStart)
                .replace("{end}", encodedEnd);
    }

    public void setServerMetrics(String serverMetrics) {
        this.serverMetrics = serverMetrics;
    }

    public String getServerActions(String serverId) {
        return baseUrl + serverActions.replace("{serverId}", serverId);
    }

    public void setServerActions(String serverActions) {
        this.serverActions = serverActions;
    }

    public String getServerAction(String serverId, String actionId) {
        return baseUrl + serverAction
                .replace("{serverId}", serverId)
                .replace("{actionId}", actionId);
    }

    public void setServerAction(String serverAction) {
        this.serverAction = serverAction;
    }

    public String getServerPowerOn(String serverId) {
        return baseUrl + serverPowerOn.replace("{serverId}", serverId);
    }

    public void setServerPowerOn(String serverPowerOn) {
        this.serverPowerOn = serverPowerOn;
    }

    public String getServerSoftReboot(String serverId) {
        return baseUrl + serverSoftReboot.replace("{serverId}", serverId);
    }

    public void setServerSoftReboot(String serverSoftReboot) {
        this.serverSoftReboot = serverSoftReboot;
    }

    public String getServerReset(String serverId) {
        return baseUrl + serverReset.replace("{serverId}", serverId);
    }

    public void setServerReset(String serverReset) {
        this.serverReset = serverReset;
    }

    public String getServerShutdown(String serverId) {
        return baseUrl + serverShutdown.replace("{serverId}", serverId);
    }

    public void setServerShutdown(String serverShutdown) {
        this.serverShutdown = serverShutdown;
    }

    public String getServerPowerOff(String serverId) {
        return baseUrl + serverPowerOff.replace("{serverId}", serverId);
    }

    public void setServerPowerOff(String serverPowerOff) {
        this.serverPowerOff = serverPowerOff;
    }

    public String getServerResetRootPassword(String serverId) {
        return baseUrl + serverResetRootPassword.replace("{serverId}", serverId);
    }

    public void setServerResetRootPassword(String serverResetRootPassword) {
        this.serverResetRootPassword = serverResetRootPassword;
    }

    public String getServerEnableRescueMode(String serverId) {
        return baseUrl + serverEnableRescueMode.replace("{serverId}", serverId);
    }

    public void setServerEnableRescueMode(String serverEnableRescueMode) {
        this.serverEnableRescueMode = serverEnableRescueMode;
    }

    public String getServerDisableRescueMode(String serverId) {
        return baseUrl + serverDisableRescueMode.replace("{serverId}", serverId);
    }

    public void setServerDisableRescueMode(String serverDisableRescueMode) {
        this.serverDisableRescueMode = serverDisableRescueMode;
    }

    public String getServerCreateImage(String serverId) {
        return baseUrl + serverCreateImage.replace("{serverId}", serverId);
    }

    public void setServerCreateImage(String serverCreateImage) {
        this.serverCreateImage = serverCreateImage;
    }

    public String getServerRebuild(String serverId) {
        return baseUrl + serverRebuild.replace("{serverId}", serverId);
    }

    public void setServerRebuild(String serverRebuild) {
        this.serverRebuild = serverRebuild;
    }

    public String getServerChangeType(String serverId) {
        return baseUrl + serverChangeType.replace("{serverId}", serverId);
    }

    public void setServerChangeType(String serverChangeType) {
        this.serverChangeType = serverChangeType;
    }

    public String getServerEnableBackup(String serverId) {
        return baseUrl + serverEnableBackup.replace("{serverId}", serverId);
    }

    public void setServerEnableBackup(String serverEnableBackup) {
        this.serverEnableBackup = serverEnableBackup;
    }

    public String getServerDisableBackup(String serverId) {
        return baseUrl + serverDisableBackup.replace("{serverId}", serverId);
    }

    public void setServerDisableBackup(String serverDisableBackup) {
        this.serverDisableBackup = serverDisableBackup;
    }

    public String getServerAttachIso(String serverId) {
        return baseUrl + serverAttachIso.replace("{serverId}", serverId);
    }

    public void setServerAttachIso(String serverAttachIso) {
        this.serverAttachIso = serverAttachIso;
    }

    public String getServerDetachIso(String serverId) {
        return baseUrl + serverDetachIso.replace("{serverId}", serverId);
    }

    public void setServerDetachIso(String serverDetachIso) {
        this.serverDetachIso = serverDetachIso;
    }

    public String getServerChangeProtection(String serverId) {
        return baseUrl + serverChangeProtection.replace("{serverId}", serverId);
    }

    public void setServerChangeProtection(String serverChangeProtection) {
        this.serverChangeProtection = serverChangeProtection;
    }

    public String getServerRequestConsole(String serverId) {
        return baseUrl + serverRequestConsole.replace("{serverId}", serverId);
    }

    public void setServerRequestConsole(String serverRequestConsole) {
        this.serverRequestConsole = serverRequestConsole;
    }


    public static HetznerCloudClientUrlConfig withDefaults() {
        return new HetznerCloudClientUrlConfig();
    }

    public static HetznerCloudClientUrlConfig withBaseUrl(String baseUrl) {
        final HetznerCloudClientUrlConfig config = new HetznerCloudClientUrlConfig();
        config.setBaseUrl(baseUrl);
        return config;
    }

}
