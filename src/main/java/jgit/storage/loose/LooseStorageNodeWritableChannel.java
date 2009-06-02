package jgit.storage.loose;

import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channels;
import java.util.zip.DeflaterOutputStream;

class LooseStorageNodeWritableChannel implements WritableByteChannel {
    private static final String TEMP_FILE_NAME_PREFIX = "temp_obj_";

    private static final int TEMP_FILE_NAME_SUFFEX_LENGTH = 6;

    private LooseStorageLayout storageLayout;

    private String objectName;

    private File tempObjectFile;

    private WritableByteChannel tempObjectFileChannel;

    private File realObjectFile;

    private void initObjectFile() throws IOException {
        File objectDir = storageLayout.getObjectDirForName(objectName);
        if (!objectDir.isDirectory()) {
            if (objectDir.exists() || !objectDir.mkdir()) {
                throw new IOException("Can't create object directory");
            }
        }
        tempObjectFile = new File(objectDir,
                TEMP_FILE_NAME_PREFIX + RandomStringUtils.randomNumeric(TEMP_FILE_NAME_SUFFEX_LENGTH));
        if (!tempObjectFile.createNewFile()) {
            throw new IOException("Can't create temp file for object");
        }
        realObjectFile = storageLayout.getObjectFileForName(objectName);
        tempObjectFileChannel = Channels.newChannel(new DeflaterOutputStream(new FileOutputStream(tempObjectFile)));
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
