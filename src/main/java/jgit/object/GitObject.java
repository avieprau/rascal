package jgit.object;

import jgit.storage.ReadableChannelFactory;

import java.nio.channels.ReadableByteChannel;
import java.io.IOException;

public class GitObject {
    private String name;

    private GitObjectType type;

    private ReadableChannelFactory channelFactory;

    public GitObject(String name, GitObjectType type, ReadableChannelFactory channelFactory) {
        this.name = name;
        this.type = type;
        this.channelFactory = channelFactory;
    }

    public String getName() {
        return name;
    }

    public GitObjectType getType() {
        return type;
    }

    public ReadableByteChannel createChannel() throws IOException {
        return channelFactory.createChannel();
    }
}
