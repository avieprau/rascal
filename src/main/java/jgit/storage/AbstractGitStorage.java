package jgit.storage;

import jgit.object.GitObject;
import jgit.object.storage.GitObjectStorageNode;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;
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

    public GitObject addObject(ObjectSource source) throws IOException {
        String objectName = objectNameResolver.getObjectName(source);
        source.copyTo(objectStorageNode.getWritableChannel(objectName));
        // TODO: channel for object and object type
        return new GitObject(objectName, null, null);
    }
}
