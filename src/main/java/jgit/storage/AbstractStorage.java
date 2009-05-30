package jgit.storage;

import jgit.object.GitObject;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;

import java.io.IOException;

public abstract class AbstractStorage implements Storage {
    private ObjectNameResolver objectNameResolver;

    protected AbstractStorage(ObjectNameResolver objectNameResolver) {
        this.objectNameResolver = objectNameResolver;
    }

    protected abstract ReadableChannelFactory getReadableChannelFactory(String objectName);

    protected abstract WritableChannelFactory getWritableChannelFactory(String objectName);

    public GitObject getObject(String name) throws IOException {
        return null;
    }

    public GitObject addObject(ObjectSource source) throws IOException {
        String objectName = objectNameResolver.getObjectName(source);
        source.copyTo(getWritableChannelFactory(objectName).createChannel());
        // TODO: channel for object and object type
        return new GitObject(objectName, null, null);
    }
}
