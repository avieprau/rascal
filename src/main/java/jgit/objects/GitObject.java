package jgit.objects;

import java.io.InputStream;

public abstract class GitObject {
    public String getName() {
        return null;
    }

    public InputStream getContentStream() {
        return null;
    }

    public long getSize() {
        return 0;
    }
}
