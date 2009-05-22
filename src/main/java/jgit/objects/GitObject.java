package jgit.objects;

import java.nio.channels.ReadableByteChannel;

public class GitObject {
    private String name;

    private GitObjectType type;

    private ReadableByteChannel channel;

    public GitObject(String name, GitObjectType type, ReadableByteChannel channel) {
        this.name = name;
        this.type = type;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public GitObjectType getType() {
        return type;
    }

    public ReadableByteChannel getChannel() {
        return channel;
    }
}
