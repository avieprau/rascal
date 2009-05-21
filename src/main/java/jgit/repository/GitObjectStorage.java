package jgit.repository;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.io.IOException;

public interface GitObjectStorage {
    WritableByteChannel getWritableChannel(String objectName) throws IOException;

    ReadableByteChannel getReadableChannel(String objectName) throws IOException;
}
