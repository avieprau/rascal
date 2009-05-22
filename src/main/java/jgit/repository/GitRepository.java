package jgit.repository;

import jgit.objects.GitObject;
import jgit.objects.source.BlobSource;

import java.io.IOException;

public interface GitRepository {
    GitObject getObject(String name) throws IOException;

    GitObject addBlob(BlobSource source) throws IOException;
}
