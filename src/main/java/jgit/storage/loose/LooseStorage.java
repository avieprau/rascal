package jgit.storage.loose;

import jgit.object.ObjectFactory;
import jgit.object.name.ObjectNameResolver;
import jgit.storage.AbstractStorage;
import jgit.storage.WritableChannelFactory;

public class LooseStorage extends AbstractStorage {
    private LooseStorageLayout storageLayout;

    private ObjectFactory objectFactory;

    public LooseStorage(LooseStorageLayout storageLayout, ObjectNameResolver objectNameResolver) {
        super(objectNameResolver);
        this.storageLayout = storageLayout;
        objectFactory = new LooseObjectFactory(storageLayout);
    }

    protected ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    protected WritableChannelFactory getWritableChannelFactory(String objectName) {
        return new LooseStorageNodeWritableChannelFactory(storageLayout, objectName);
    }
}
