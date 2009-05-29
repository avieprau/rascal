package jgit.storage;

import jgit.object.GitObject;
import jgit.object.GitObjectType;
import jgit.object.storage.GitObjectStorageNode;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.BlobSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class AbstractGitStorage implements GitStorage {
    @Autowired
    private ObjectNameResolver objectNameResolver;

    @Autowired
    private GitObjectStorageNode objectStorageNode;

    public GitObject getObject(String name) throws IOException {
        return null;
    }

    public GitObject addBlob(BlobSource source) throws IOException {
        String objectName = objectNameResolver.getBlobName(source);
        source.copyTo(objectStorageNode.getWritableChannel(objectName));
        // TODO: channel for blob
        return new GitObject(objectName, GitObjectType.BLOB, null);
    }
}
