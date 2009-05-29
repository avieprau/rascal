package jgit.storage;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public interface ObjectStorageNode {
    WritableByteChannel getWritableChannel(String objectName) throws IOException;

    ReadableByteChannel getReadableChannel(String objectName) throws IOException;
}
