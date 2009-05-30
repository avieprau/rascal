package jgit.object.source;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class FileChannelBlobSource extends BlobSource {
    private FileChannel source;

    public FileChannelBlobSource(FileChannel source) {
        this.source = source;
    }

    protected long getBlobSize() throws IOException {
        return source.size();
    }

    protected void copyBlobDataTo(WritableByteChannel destination) throws IOException {
        source.transferTo(0, source.size(), destination);
    }
}
