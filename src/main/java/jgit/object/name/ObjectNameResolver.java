package jgit.object.name;

import jgit.object.source.BlobSource;

import java.io.IOException;

public interface ObjectNameResolver {
    String getBlobName(BlobSource source) throws IOException;
}
