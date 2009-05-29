package jgit.storage.loose;

import jgit.storage.FileSystemStorageLayout;

import java.io.File;

public interface LooseStorageLayout extends FileSystemStorageLayout {
    File getObjectsDir();
}
