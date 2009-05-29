package jgit.object.source;

import java.nio.channels.WritableByteChannel;
import java.io.IOException;

public interface ObjectSource {
    void copyTo(WritableByteChannel destination) throws IOException;
}
