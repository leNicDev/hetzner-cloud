package de.lenicdev.hetznercloud;

import de.lenicdev.hetznercloud.model.Action;
import de.lenicdev.hetznercloud.model.Server;
import de.lenicdev.hetznercloud.model.exception.HetznerCloudException;
import de.lenicdev.hetznercloud.model.request.CreateServerRequest;
import de.lenicdev.hetznercloud.model.response.CreateServerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class HetznerCloudClientTests {

    private HetznerCloudClient client;


    @BeforeEach
    void setup() {
        // Get API token from environment variables
        final String apiToken = System.getenv("HETZNER_API_TOKEN");

        // Initialize client
        this.client = new HetznerCloudClient(apiToken);
    }


    @Nested
    class CreateServer {
        @Test
        void createNewServerTest() throws IOException, HetznerCloudException {
            // Build request
            final CreateServerRequest request = new CreateServerRequest.Builder()
                    .name("test-server")
                    .serverType("cx11")
                    .location("nbg1")
                    .startAfterCreate(false)
                    .image("ubuntu-18.04")
                    .userData("#cloud-config\nruncmd:\n- [touch, /root/cloud-init-worked]\n")
                    .automount(false)
                    .build();

            final CreateServerResponse response = client.createServer(request);

            assert response != null;
        }
    }

    @Nested
    class GetServer {
        @Test
        void getAllServersTest() throws IOException, HetznerCloudException {
            final List<Server> response = client.getAllServers();

            assert response != null;
        }

        @Test
        void getServerTest() throws IOException, HetznerCloudException {
            final String serverId = "2331261";
            final Server server = client.getServer(serverId);

            assert server != null;
        }
    }

    @Nested
    class GetServerActions {
        @Test
        void getAllServerActionsTest() throws IOException, HetznerCloudException {
            final List<Action> response = client.getAllServerActions("2331261");

            assert response != null;
        }
        @Test
        void getServerActionTest() throws IOException, HetznerCloudException {
            final Action response = client.getServerAction("2331261", "18392591");

            assert response != null;
        }
    }

    @Nested
    class ServerPowerOn {
        @Test
        void powerOnServerTest() throws IOException, HetznerCloudException {
            final Action response = client.powerOnServer("2331261");

            assert response != null;
        }
    }

}
