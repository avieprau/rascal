package jgit.repository.loose;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;

class LooseGitObjectWritableByteChannel implements WritableByteChannel {
    private File tempObjectFile;

    private FileChannel tempObjectFileChannel;

    private File realObjectFile;

    private File getTempObjectFile(String objectName) {
        return null;
    }

    private File getRealObjectFile(String objectName) {
        return null;
    }

    LooseGitObjectWritableByteChannel(String objectName) throws IOException {
        tempObjectFile = getTempObjectFile(objectName);
        realObjectFile = getRealObjectFile(objectName);
        tempObjectFileChannel = new FileOutputStream(tempObjectFile).getChannel();
    }

    public synchronized int write(ByteBuffer src) throws IOException {
        return 0;
    }

    public boolean isOpen() {
        return false;
    }

    public synchronized void close() throws IOException {

    }
}
