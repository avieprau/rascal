package jgit.storage.loose;

import jgit.storage.ObjectStorageNode;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class LooseObjectStorageNode implements ObjectStorageNode {
    public WritableByteChannel getWritableChannel(String objectName) throws IOException {
        return null;
    }

    public ReadableByteChannel getReadableChannel(String objectName) throws IOException {
        return null;
    }
}
