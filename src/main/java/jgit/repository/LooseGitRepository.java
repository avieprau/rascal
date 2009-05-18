package jgit.repository;

import jgit.objects.GitObject;

import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class LooseGitRepository extends GitRepository {
    @Autowired
    private LooseObjectOutputStreamFactory outputStreamFactory;

    @Override
    public GitObject getObject(String name) {
        return null;
    }

    @Override
    public GitObject putBlob(InputStream contentStream) {
        return null;
    }
}
