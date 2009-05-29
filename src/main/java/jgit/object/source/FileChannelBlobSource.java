package jgit.object.source;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.FileChannel;
import java.io.IOException;

public class FileChannelBlobSource extends BlobSource {
    private FileChannel source;

    public FileChannelBlobSource(FileChannel source) {
        this.source = source;
    }

    @Override
    protected long getBlobSize() throws IOException {
        return source.size();
    }

    @Override
    protected void copyBlobDataTo(WritableByteChannel destination) throws IOException {
        source.transferTo(0, source.size(), destination);
    }
}
