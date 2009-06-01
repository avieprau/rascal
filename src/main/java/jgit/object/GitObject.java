package jgit.object;

import jgit.storage.ReadableChannelFactory;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public class GitObject {
    private String name;

    private ReadableChannelFactory channelFactory;

    GitObject(String name, ReadableChannelFactory channelFactory) {
        this.name = name;
        this.channelFactory = channelFactory;
    }

    public String getName() {
        return name;
    }

    public GitObjectType getType() {
        // TODO: read object type from object channel
        return null;
    }

    public ReadableByteChannel createChannel() throws IOException {
        return channelFactory.createChannel();
    }
}
