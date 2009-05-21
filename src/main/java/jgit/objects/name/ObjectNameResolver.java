package jgit.objects.name;

import jgit.objects.source.BlobSource;

import java.io.IOException;

public interface ObjectNameResolver {
    String getBlobName(BlobSource source) throws IOException;
}
