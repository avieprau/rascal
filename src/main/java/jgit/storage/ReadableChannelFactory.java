package jgit.storage;

import java.nio.channels.ReadableByteChannel;
import java.io.IOException;

public interface ReadableChannelFactory {
    ReadableByteChannel createChannel() throws IOException;
}
