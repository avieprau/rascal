package jgit.object.storage;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.io.IOException;

public interface ObjectStorageNode {
    WritableByteChannel getWritableChannel(String objectName) throws IOException;

    ReadableByteChannel getReadableChannel(String objectName) throws IOException;
}
