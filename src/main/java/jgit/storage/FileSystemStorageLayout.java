package jgit.storage;

import java.io.File;

public interface FileSystemStorageLayout extends StorageLayout {
    File getRepositoryDir();
}
