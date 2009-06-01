package jgit.storage.loose;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

class LooseStorageNodeReadableChannel implements ReadableByteChannel {
    private FileChannel objectFileChannel;

    public LooseStorageNodeReadableChannel(LooseStorageLayout storageLayout, String objectName) throws IOException {
        objectFileChannel = new FileInputStream(storageLayout.getObjectFileForName(objectName)).getChannel();
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
