package jgit.storage.loose;

import jgit.object.name.ObjectNameResolver;
import jgit.storage.AbstractStorage;
import jgit.storage.ReadableChannelFactory;
import jgit.storage.WritableChannelFactory;

public class LooseStorage extends AbstractStorage {
    private LooseStorageLayout storageLayout;

    public LooseStorage(LooseStorageLayout storageLayout, ObjectNameResolver objectNameResolver) {
        super(objectNameResolver);
        this.storageLayout = storageLayout;
    }

    protected ReadableChannelFactory getReadableChannelFactory(String objectName) {
        // TODO: readable channel factory creation
        return null;
    }

    protected WritableChannelFactory getWritableChannelFactory(String objectName) {
        return new LooseStorageNodeWritableChannelFactory(storageLayout, objectName);
    }
}
