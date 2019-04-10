package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Action;

public class GetServerActionResponse extends HetznerCloudResponse {

    private Action action;


    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

}
