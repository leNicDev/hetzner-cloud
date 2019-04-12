package de.lenicdev.hetznercloud;

import com.github.tomakehurst.wiremock.WireMockServer;
import de.lenicdev.hetznercloud.model.Action;
import de.lenicdev.hetznercloud.model.Server;
import de.lenicdev.hetznercloud.model.exception.HetznerCloudException;
import de.lenicdev.hetznercloud.model.request.*;
import de.lenicdev.hetznercloud.model.response.*;
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

        // Get a server
        wireMockServer.stubFor(get(urlMatching("/v1/servers/[0-9]+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/get_server.json")));

        // Update a server
        wireMockServer.stubFor(put(urlMatching("/v1/servers/[0-9]+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/update_server.json")));

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

        // Reboot server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/reboot"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_reboot.json")));

        // Reset server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/reset"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_reset.json")));

        // Shutdown server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/shutdown"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_shutdown.json")));

        // Power off server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/poweroff"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_poweroff.json")));

        // Reset server root password
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/reset_password"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_reset_password.json")));

        // Enable server rescue mode
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/enable_rescue"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_enable_rescue.json")));

        // Disable server rescue mode
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/disable_rescue"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_disable_rescue.json")));

        // Create server image
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/create_image"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_create_image.json")));

        // Rebuild server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/rebuild"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_rebuild.json")));

        // Change server type
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/change_type"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_change_type.json")));

        // Enable server backups
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/enable_backup"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_enable_backup.json")));

        // Disable server backups
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/disable_backup"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_disable_backup.json")));

        // Attach ISO to server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/attach_iso"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_attach_iso.json")));

        // Detach ISO from server
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/detach_iso"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_detach_iso.json")));

        // Change server protection
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/change_protection"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_change_protection.json")));

        // Request server console
        wireMockServer.stubFor(post(urlMatching("/v1/servers/[0-9]+/actions/request_console"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/server_request_console.json")));
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
    class UpdateServer {
        @Test
        void updateServerTest() throws IOException, HetznerCloudException {
            final UpdateServerRequest request = new UpdateServerRequest("42");
            request.setName("new-name");

            final Server server = client.updateServer(request);

            Assert.assertNotNull(server);
        }
    }

    @Nested
    class GetServerActions {
        @Test
        void getAllServerActionsTest() throws IOException, HetznerCloudException {
            final List<Action> response = client.getAllServerActions("42");

            assert response != null;
        }
        @Test
        void getServerActionTest() throws IOException, HetznerCloudException {
            final Action response = client.getServerAction("42", "18392591");

            assert response != null;
        }
    }

    @Nested
    class ServerPower {
        @Test
        void powerOnServerTest() throws IOException, HetznerCloudException {
            final Action response = client.powerOnServer("42");

            assert response != null;
        }

        @Test
        void powerOffServerTest() throws IOException, HetznerCloudException {
            final Action response = client.powerOffServer("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class RebootServer {
        @Test
        void rebootServerTest() throws IOException, HetznerCloudException {
            final Action response = client.rebootServer("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ResetServer {
        @Test
        void resetServerTest() throws IOException, HetznerCloudException {
            final Action response = client.resetServer("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ShutdownServer {
        @Test
        void shutdownServerTest() throws IOException, HetznerCloudException {
            final Action response = client.shutdownServer("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ResetServerRootPassword {
        @Test
        void resetServerRootPasswordTest() throws IOException, HetznerCloudException {
            final ServerResetRootPasswordResponse response = client.resetServerRootPassword("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class RescueMode {
        @Test
        void enableServerRescueModeTest() throws IOException, HetznerCloudException {
            final ServerEnableRescueModeRequest request = new ServerEnableRescueModeRequest(
                    "42", "linux64");
            final ServerEnableRescueModeResponse response = client.enableRescueMode(request);

            Assert.assertNotNull(response);
        }
        @Test
        void disableServerRescueModeTest() throws IOException, HetznerCloudException {
            final Action response = client.disableRescueMode("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ServerImage {
        @Test
        void createServerImageTest() throws IOException, HetznerCloudException {
            final ServerCreateImageRequest request = new ServerCreateImageRequest(
                    "42", "Test image", "snapshot");
            final ServerCreateImageResponse response = client.createServerImage(request);

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class RebuildServer {
        @Test
        void rebuildServerTest() throws IOException, HetznerCloudException {
            final ServerRebuildResponse response = client.rebuildServer("42", "image_name");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ChangeServerType {
        @Test
        void changeServerTypeTest() throws IOException, HetznerCloudException {
            final ServerChangeTypeRequest request = new ServerChangeTypeRequest(
                    "42", true, "cx21");
            final Action response = client.changeServerType(request);

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ServerBackup {
        @Test
        void enableServerBackupTest() throws IOException, HetznerCloudException {
            final Action response = client.enableServerBackup("42");

            Assert.assertNotNull(response);
        }

        @Test
        void disableServerBackupTest() throws IOException, HetznerCloudException {
            final Action response = client.disableServerBackup("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ServerIso {
        @Test
        void attachServerIsoTest() throws IOException, HetznerCloudException {
            final Action response = client.attachIsoToServer("42", "test-iso");

            Assert.assertNotNull(response);
        }

        @Test
        void detachServerIsoTest() throws IOException, HetznerCloudException {
            final Action response = client.detachIsoFromServer("42");

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ChangeServerProtection {
        @Test
        void changeServerProtectionTest() throws IOException, HetznerCloudException {
            final ServerChangeProtectionRequest request = new ServerChangeProtectionRequest(
                    "42", true, true);
            final Action response = client.changeServerProtection(request);

            Assert.assertNotNull(response);
        }
    }

    @Nested
    class ServerConsole {
        @Test
        void requestServerConsoleTest() throws IOException, HetznerCloudException {
            final ServerConsoleResponse response = client.requestServerConsole("42");

            Assert.assertNotNull(response);
        }
    }

}
