package jgit.storage.loose;

import jgit.storage.WritableChannelFactory;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

class LooseStorageNodeWritableChannelFactory implements WritableChannelFactory {
    private LooseStorageLayout storageLayout;

    private String objectName;

    LooseStorageNodeWritableChannelFactory(LooseStorageLayout storageLayout, String objectName) {
        this.storageLayout = storageLayout;
        this.objectName = objectName;
    }

    public WritableByteChannel createChannel() throws IOException {
        return new LooseStorageNodeWritableChannel(storageLayout, objectName);
    }
}
