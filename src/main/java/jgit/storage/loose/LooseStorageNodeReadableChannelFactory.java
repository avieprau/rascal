package jgit.storage.loose;

import jgit.storage.ReadableChannelFactory;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

class LooseStorageNodeReadableChannelFactory implements ReadableChannelFactory {
    private LooseStorageLayout storageLayout;

    private String objectName;

    LooseStorageNodeReadableChannelFactory(LooseStorageLayout storageLayout, String objectName) {
        this.storageLayout = storageLayout;
        this.objectName = objectName;
    }

    public ReadableByteChannel createChannel() throws IOException {
        return new LooseStorageNodeReadableChannel(storageLayout, objectName);
    }
}
