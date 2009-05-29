package jgit.storage.loose;

import jgit.object.storage.ObjectStorageNode;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.io.IOException;

public class LooseObjectStorageNode implements ObjectStorageNode {
    public WritableByteChannel getWritableChannel(String objectName) throws IOException {
        return null;
    }

    public ReadableByteChannel getReadableChannel(String objectName) throws IOException {
        return null;
    }
}
