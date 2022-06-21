package org.acme.config;

import org.eclipse.microprofile.config.inject.ConfigProperties;

@ConfigProperties(prefix = "storage")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
