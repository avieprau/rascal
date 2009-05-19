package jgit.repository;

import jgit.objects.GitObject;

import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class PackedGitRepository implements GitRepository {
    public GitObject getObject(String name) {
        return null;
    }

    public GitObject putBlob(InputStream contentStream) {
        return null;
    }
}
