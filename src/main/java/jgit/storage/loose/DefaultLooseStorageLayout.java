package jgit.storage.loose;

import jgit.storage.DefaultFileSystemStorageLayout;

import java.io.File;

public class DefaultLooseStorageLayout extends DefaultFileSystemStorageLayout implements LooseStorageLayout {
    private static final String OBJECT_DIR_NAME = "objects";

    public File getObjectsDir() {
        return new File(getRepositoryDir(), OBJECT_DIR_NAME);
    }
}
