package de.lenicdev.hetznercloud;

import com.github.tomakehurst.wiremock.WireMockServer;
import de.lenicdev.hetznercloud.model.Action;
import de.lenicdev.hetznercloud.model.Server;
import de.lenicdev.hetznercloud.model.exception.HetznerCloudException;
import de.lenicdev.hetznercloud.model.request.CreateServerRequest;
import de.lenicdev.hetznercloud.model.response.CreateServerResponse;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class HetznerCloudClientTests {

    private HetznerCloudClient client;

    WireMockServer wireMockServer;


    @BeforeEach
    void setup() {
        // Initialize WireMock server
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();

        setupStub();

        // Initialize client
        this.client = new HetznerCloudClient("no-api-token-required-for-testing");
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    public void setupStub() {
        // Create server
        wireMockServer.stubFor(post(urlEqualTo("/v1/servers"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/create_server.json")));

        // Get all servers
        wireMockServer.stubFor(get(urlEqualTo("/v1/servers"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/get_all_servers.json")));

        // Get a servers
        wireMockServer.stubFor(get(urlMatching("/v1/servers/[0-9]+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/get_server.json")));

        // Get server actions
        wireMockServer.stubFor(get(urlMatching("/v1/servers/[0-9]+/actions"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/get_all_server_actions.json")));

        // Get server action
        wireMockServer.stubFor(get(urlMatching("/v1/servers/[0-9]+/actions/[0-9]+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/get_server_action.json")));

        // Power on server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/poweron"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_power_on.json")));
    }


    @Nested
    class CreateServer {
        @Test
        void createNewServerTest() throws IOException, HetznerCloudException {
            // Build request
            final CreateServerRequest request = new CreateServerRequest.Builder()
                    .name("test-server")
                    .serverType("cx11")
                    .location("fsn1")
                    .startAfterCreate(false)
                    .image("ubuntu-16.04")
                    .userData("#cloud-config\nruncmd:\n- [touch, /root/cloud-init-worked]\n")
                    .automount(false)
                    .build();

            final CreateServerResponse response = client.createServer(request);

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class GetServer {
        @Test
        void getAllServersTest() throws IOException, HetznerCloudException {
            final List<Server> response = client.getAllServers();

            assert response != null && !response.isEmpty();
        }

        @Test
        void getServerTest() throws IOException, HetznerCloudException {
            final String serverId = "42";
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
