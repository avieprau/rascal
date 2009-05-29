package jgit.storage;

import jgit.object.GitObject;
import jgit.object.source.BlobSource;

import java.io.IOException;

public interface GitStorage {
    GitObject getObject(String name) throws IOException;

    GitObject addBlob(BlobSource source) throws IOException;
}
