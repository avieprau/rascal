package jgit.storage.loose;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import java.util.zip.InflaterInputStream;

class LooseStorageNodeReadableChannel implements ReadableByteChannel {
    private ReadableByteChannel objectFileChannel;

    public LooseStorageNodeReadableChannel(LooseStorageLayout storageLayout, String objectName) throws IOException {
        File objectFile = storageLayout.getObjectFileForName(objectName);
        objectFileChannel = Channels.newChannel(new InflaterInputStream(new FileInputStream(objectFile)));
    }

    public int read(ByteBuffer dst) throws IOException {
        return objectFileChannel.read(dst);
    }

    public boolean isOpen() {
        return objectFileChannel.isOpen();
    }

    public void close() throws IOException {
        objectFileChannel.close();
    }
}
