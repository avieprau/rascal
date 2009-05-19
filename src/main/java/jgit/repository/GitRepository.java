package jgit.repository;

import jgit.objects.GitObject;

import java.io.InputStream;

public interface GitRepository {
    GitObject getObject(String name);

    GitObject putBlob(InputStream contentStream);
}
