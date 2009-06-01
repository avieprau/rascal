package jgit.storage;

import jgit.object.AbstractObjectFactory;
import jgit.object.CorruptedObjectException;
import jgit.object.GitObject;
import jgit.object.ObjectFactory;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public abstract class AbstractStorage implements Storage {
    private ObjectNameResolver objectNameResolver;

    private ObjectFactory objectFactory = new AbstractObjectFactory() {
        protected ReadableByteChannel getChannel(String objectName) throws IOException {
            return getReadableChannelFactory(objectName).createChannel();
        }
    };

    protected AbstractStorage(ObjectNameResolver objectNameResolver, ObjectFactory objectFactory) {
        this.objectNameResolver = objectNameResolver;
        this.objectFactory = objectFactory;
    }

    protected abstract ReadableChannelFactory getReadableChannelFactory(String objectName);

    protected abstract WritableChannelFactory getWritableChannelFactory(String objectName);


    public GitObject getObject(String name) throws IOException, CorruptedObjectException {
        return objectFactory.createObject(name);
    }

    public void addObject(ObjectSource source) throws IOException {
        String objectName = objectNameResolver.getObjectName(source);
        source.copyTo(getWritableChannelFactory(objectName).createChannel());
    }
}
