package jgit.object.source;

import jgit.object.GitObjectType;

import java.nio.channels.WritableByteChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

import org.apache.commons.lang.ArrayUtils;

public abstract class BlobSource implements ObjectSource {
    protected abstract long getBlobSize() throws IOException;

    protected abstract void copyBlobDataTo(WritableByteChannel destination) throws IOException;

    public final void copyTo(WritableByteChannel destination) throws IOException {
        String headerString = String.format("%s %d", GitObjectType.BLOB, getBlobSize());
        byte[] header = ArrayUtils.add(headerString.getBytes(), (byte) 0);
        destination.write(ByteBuffer.wrap(header));
        copyBlobDataTo(destination);
    }
}
