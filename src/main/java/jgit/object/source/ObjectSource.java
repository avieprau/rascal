package jgit.object.source;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public interface ObjectSource {
    void copyTo(WritableByteChannel destination) throws IOException;
}
