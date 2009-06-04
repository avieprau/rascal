/*
 * Copyright 2009, Andrej Viepraŭ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rascal.storage.loose;

import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
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
