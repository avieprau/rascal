package jgit.storage;

import jgit.object.GitObject;
import jgit.object.ObjectFactory;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;

import java.io.IOException;

public abstract class AbstractStorage implements Storage {
    private ObjectNameResolver objectNameResolver;

    private ObjectFactory objectFactory;

    protected AbstractStorage(ObjectNameResolver objectNameResolver, ObjectFactory objectFactory) {
        this.objectNameResolver = objectNameResolver;
        this.objectFactory = objectFactory;
    }

    protected abstract ReadableChannelFactory getReadableChannelFactory(String objectName);

    protected abstract WritableChannelFactory getWritableChannelFactory(String objectName);

    public GitObject getObject(String name) throws IOException {
        return objectFactory.createObject(name, getReadableChannelFactory(name));
    }

    public GitObject addObject(ObjectSource source) throws IOException {
        String objectName = objectNameResolver.getObjectName(source);
        source.copyTo(getWritableChannelFactory(objectName).createChannel());
        return objectFactory.createObject(objectName, getReadableChannelFactory(objectName));
    }
}
