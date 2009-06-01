package jgit.storage.loose;

import jgit.storage.ChannelFactoryDependentObjectFactory;
import jgit.storage.ReadableChannelFactory;

class LooseObjectFactory extends ChannelFactoryDependentObjectFactory {
    private LooseStorageLayout storageLayout;

    LooseObjectFactory(LooseStorageLayout storageLayout) {
        this.storageLayout = storageLayout;
    }

    protected ReadableChannelFactory getChannelFactory(String objectName) {
        // TODO: some cash for factories
        return new LooseStorageNodeReadableChannelFactory(storageLayout, objectName);
    }
}
