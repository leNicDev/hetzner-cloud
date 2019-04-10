package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerEnableBackupRequest extends HetznerCloudRequest {

    public ServerEnableBackupRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_ENABLE_BACKUP.replace("{serverId}", serverId));
    }

}
