package jgit.objects.source;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.FileChannel;
import java.io.IOException;

public class FileChannelBlobSource implements BlobSource {
    private FileChannel source;

    public FileChannelBlobSource(FileChannel source) {
        this.source = source;
    }

    public void copyTo(WritableByteChannel destination) throws IOException {
        source.transferTo(0, source.size(), destination);
    }
}
