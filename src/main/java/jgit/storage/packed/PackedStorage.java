package jgit.storage.packed;

import jgit.object.ObjectFactory;
import jgit.object.name.ObjectNameResolver;
import jgit.storage.AbstractStorage;
import jgit.storage.ReadableChannelFactory;
import jgit.storage.WritableChannelFactory;


public class PackedStorage extends AbstractStorage {
    public PackedStorage(ObjectNameResolver objectNameResolver, ObjectFactory objectFactory) {
        super(objectNameResolver, objectFactory);
    }

    protected ReadableChannelFactory getReadableChannelFactory(String objectName) {
        return null;
    }

    protected WritableChannelFactory getWritableChannelFactory(String objectName) {
        return null;
    }
}
