package de.lenicdev.hetznercloud.config;

/**
 * Configuration of the {@link de.lenicdev.hetznercloud.HetznerCloudClient}
 */
public class HetznerCloudClientConfig {

    private HetznerCloudClientUrlConfig urlConfig;


    public HetznerCloudClientConfig() {
        this.urlConfig = HetznerCloudClientUrlConfig.withDefaults();
    }

    public HetznerCloudClientConfig(HetznerCloudClientUrlConfig urlConfig) {
        this.urlConfig = urlConfig;
    }


    public HetznerCloudClientUrlConfig getUrlConfig() {
        return urlConfig;
    }

    public void setUrlConfig(HetznerCloudClientUrlConfig urlConfig) {
        this.urlConfig = urlConfig;
    }

}
