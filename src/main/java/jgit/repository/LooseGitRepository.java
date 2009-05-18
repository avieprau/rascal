package jgit.repository;

import jgit.objects.GitObject;

import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class LooseGitRepository extends GitRepository {
    @Override
    public GitObject getObject(String name) {
        return null;
    }

    @Override
    public GitObject putBlob(InputStream contentStream) {
        return null;
    }
}
