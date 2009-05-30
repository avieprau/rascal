package jgit.storage.loose;

import org.apache.commons.lang.math.RandomUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

class LooseStorageNodeWritableChannel implements WritableByteChannel {
    private static final int OBJECT_DIR_NAME_LENGTH = 2;

    private static final String TEMP_FILE_NAME_FORMAT = "temp_obj_%d";

    private LooseStorageLayout storageLayout;

    private String objectName;

    private File tempObjectFile;

    private FileChannel tempObjectFileChannel;

    private File realObjectFile;

    private void initObjectFile() throws IOException {
        File objectDir = new File(storageLayout.getObjectsDir(), objectName.substring(0, OBJECT_DIR_NAME_LENGTH));
        if (!objectDir.isDirectory()) {
            if (objectDir.exists() || !objectDir.mkdir()) {
                throw new IOException("Can't create object directory");
            }
        }
        tempObjectFile = new File(objectDir, String.format(TEMP_FILE_NAME_FORMAT, RandomUtils.nextInt()));
        if (!tempObjectFile.createNewFile()) {
            throw new IOException("Can't create temp file for object");
        }
        realObjectFile = new File(objectDir, objectName.substring(OBJECT_DIR_NAME_LENGTH));
        tempObjectFileChannel = new FileOutputStream(tempObjectFile).getChannel();
    }

    LooseStorageNodeWritableChannel(LooseStorageLayout storageLayout, String objectName) throws IOException {
        this.storageLayout = storageLayout;
        this.objectName = objectName;
        initObjectFile();
    }

    public synchronized int write(ByteBuffer src) throws IOException {
        return tempObjectFileChannel.write(src);
    }

    public boolean isOpen() {
        return tempObjectFileChannel.isOpen();
    }

    public synchronized void close() throws IOException {
        tempObjectFileChannel.close();
        if (!tempObjectFile.renameTo(realObjectFile)) {
            throw new IOException("Can't rename temp file");
        }
    }
}
