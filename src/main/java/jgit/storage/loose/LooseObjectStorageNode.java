package jgit.storage.loose;

import jgit.storage.ObjectStorageNode;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class LooseObjectStorageNode implements ObjectStorageNode {
    private LooseStorageLayout storageLayout;

    public LooseObjectStorageNode(LooseStorageLayout storageLayout) {
        this.storageLayout = storageLayout;
    }

    public WritableByteChannel getWritableChannel(String objectName) throws IOException {
        return new LooseStorageNodeWritableChannel(storageLayout, objectName);
    }

    public ReadableByteChannel getReadableChannel(String objectName) throws IOException {
        return null;
    }
}
