package jgit.storage.loose;

import jgit.object.name.ObjectNameResolver;
import jgit.object.ObjectFactory;
import jgit.storage.AbstractStorage;
import jgit.storage.ReadableChannelFactory;
import jgit.storage.WritableChannelFactory;

public class LooseStorage extends AbstractStorage {
    private LooseStorageLayout storageLayout;

    public LooseStorage(LooseStorageLayout storageLayout,
                        ObjectNameResolver objectNameResolver, ObjectFactory objectFactory) {
        super(objectNameResolver, objectFactory);
        this.storageLayout = storageLayout;
    }

    protected ReadableChannelFactory getReadableChannelFactory(String objectName) {
        // TODO: some cash for factories
        return new LooseStorageNodeReadableChannelFactory(storageLayout, objectName);
    }

    protected WritableChannelFactory getWritableChannelFactory(String objectName) {
        return new LooseStorageNodeWritableChannelFactory(storageLayout, objectName);
    }
}
