package jgit.repository;

import jgit.objects.Blob;
import jgit.objects.GitObject;
import jgit.objects.name.ObjectNameResolver;
import jgit.objects.source.BlobSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class AbstractGitRepository implements GitRepository {
    @Autowired
    private ObjectNameResolver objectNameResolver;

    @Autowired
    private GitObjectStorage objectStorage;

    public GitObject getObject(String name) throws IOException {
        return null;
    }

    public Blob addBlob(BlobSource source) throws IOException {
        String objectName = objectNameResolver.getBlobName(source);
        source.copyTo(objectStorage.getWritableChannel(objectName));
        return new Blob(objectName);
    }
}
