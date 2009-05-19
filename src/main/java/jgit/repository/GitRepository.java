package jgit.repository;

import jgit.objects.GitObject;
import jgit.objects.Blob;

import java.io.InputStream;
import java.io.IOException;

public interface GitRepository {
    GitObject getObject(String name) throws IOException;

    Blob putBlob(InputStream contentStream) throws IOException;
}
