package jgit.storage;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public interface ReadableChannelFactory {
    ReadableByteChannel createChannel() throws IOException;
}
