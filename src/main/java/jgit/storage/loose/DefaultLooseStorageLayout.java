package jgit.storage.loose;

import jgit.storage.DefaultFileSystemStorageLayout;

import java.io.File;

public class DefaultLooseStorageLayout extends DefaultFileSystemStorageLayout implements LooseStorageLayout {
    private static final String OBJECTS_DIR_NAME = "objects";

    private static final int OBJECT_DIR_NAME_LENGTH = 2;

    public File getObjectsDir() {
        return new File(getRepositoryDir(), OBJECTS_DIR_NAME);
    }

    public File getObjectDirForName(String objectName) {
        return new File(getObjectsDir(), objectName.substring(0, OBJECT_DIR_NAME_LENGTH));
    }

    public File getObjectFileForName(String objectName) {
        return new File(getObjectDirForName(objectName), objectName.substring(OBJECT_DIR_NAME_LENGTH));
    }
}
