package jgit.repository;

import jgit.objects.GitObject;
import jgit.objects.GitObjectType;
import jgit.objects.storage.GitObjectStorage;
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

    public GitObject addBlob(BlobSource source) throws IOException {
        String objectName = objectNameResolver.getBlobName(source);
        source.copyTo(objectStorage.getWritableChannel(objectName));
        // TODO: channel for blob
        return new GitObject(objectName, GitObjectType.BLOB, null);
    }
}
