package jgit.storage;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public interface WritableChannelFactory {
    WritableByteChannel createChannel() throws IOException;
}
