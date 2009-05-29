package jgit.object.source;

import jgit.object.GitObjectType;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

import org.apache.commons.lang.ArrayUtils;

public class FileChannelBlobSource implements ObjectSource {
    private FileChannel source;

    public FileChannelBlobSource(FileChannel source) {
        this.source = source;
    }

    public void copyTo(WritableByteChannel destination) throws IOException {
        String headerString = String.format("%s %d", GitObjectType.BLOB, source.size());
        byte[] header = ArrayUtils.add(headerString.getBytes(), (byte) 0);
        destination.write(ByteBuffer.wrap(header));
        source.transferTo(0, source.size(), destination);
    }
}
