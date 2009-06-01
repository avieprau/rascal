package jgit.object;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public abstract class GitObject {
    private String name;

    private GitObjectType type;

    private long size;

    GitObject(String name, GitObjectType type, long size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public GitObjectType getType() {
        return type;
    }

    public long getSize() {
        return size;
    }

    public abstract ReadableByteChannel getChannel() throws IOException;
}
