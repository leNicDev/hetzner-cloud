package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerDisableBackupRequest extends HetznerCloudRequest {

    public ServerDisableBackupRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_DISABLE_BACKUP.replace("{serverId}", serverId));
    }

}
