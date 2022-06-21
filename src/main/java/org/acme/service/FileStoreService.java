package org.acme.service;

import org.acme.config.StorageProperties;

import javax.inject.Singleton;
import java.nio.file.Path;
import java.nio.file.Paths;

@Singleton
public class FileStoreService {

    private StorageProperties properties = new StorageProperties();
    Path path = Paths.get(properties.getLocation());

}
