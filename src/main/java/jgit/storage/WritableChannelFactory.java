package jgit.storage;

import java.nio.channels.WritableByteChannel;
import java.io.IOException;

public interface WritableChannelFactory {
    WritableByteChannel createChannel() throws IOException;
}
