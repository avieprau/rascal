package jgit.objects;

import java.io.InputStream;

public abstract class GitObject {
    private String name;

    protected GitObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public InputStream getContentStream() {
        return null;
    }

    public long getSize() {
        return 0;
    }
}
