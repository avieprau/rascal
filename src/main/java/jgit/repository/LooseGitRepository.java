package jgit.repository;

import jgit.objects.GitObject;

import java.io.InputStream;

public class LooseGitRepository extends GitRepository {
    @Override
    public GitObject getObject(String name) {
        return null;
    }

    @Override
    public GitObject putObject(InputStream contentStream) {
        return null;
    }
}
