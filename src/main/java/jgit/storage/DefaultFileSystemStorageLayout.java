package jgit.storage;

import java.io.File;

public class DefaultFileSystemStorageLayout implements FileSystemStorageLayout {
    private static final String REPOSITORY_DIR_NAME = ".git";

    public File getRepositoryDir() {
        return new File(REPOSITORY_DIR_NAME);
    }
}
