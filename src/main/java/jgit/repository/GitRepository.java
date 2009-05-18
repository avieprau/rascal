package jgit.repository;

import jgit.objects.GitObject;

import java.io.InputStream;

public abstract class GitRepository {
    public abstract GitObject getObject(String name);

    public abstract GitObject putObject(InputStream contentStream);
}
