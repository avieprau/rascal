package jgit.objects.source;

import java.nio.channels.WritableByteChannel;
import java.io.IOException;

public interface BlobSource {
    void copyTo(WritableByteChannel destination) throws IOException;
}
