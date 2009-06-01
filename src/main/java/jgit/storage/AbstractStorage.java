package jgit.storage;

import jgit.object.CorruptedObjectException;
import jgit.object.GitObject;
import jgit.object.ObjectFactory;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;

import java.io.IOException;

public abstract class AbstractStorage implements Storage {
    private ObjectNameResolver objectNameResolver;

    protected AbstractStorage(ObjectNameResolver objectNameResolver) {
        this.objectNameResolver = objectNameResolver;
    }

    protected abstract ObjectFactory getObjectFactory();

    protected abstract WritableChannelFactory getWritableChannelFactory(String objectName);

    public GitObject getObject(String name) throws IOException, CorruptedObjectException {
        return getObjectFactory().createObject(name);
    }

    public void addObject(ObjectSource source) throws IOException {
        String objectName = objectNameResolver.getObjectName(source);
        source.copyTo(getWritableChannelFactory(objectName).createChannel());
    }
}
