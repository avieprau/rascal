package jgit.repository;

import jgit.objects.GitObject;

import java.io.InputStream;
import java.security.MessageDigest;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class LooseGitRepository implements GitRepository {
    @Autowired
    private MessageDigest sha1MessageDigest;

    @Autowired
    private LooseObjectOutputStreamFactory outputStreamFactory;

    public GitObject getObject(String name) {
        return null;
    }

    public GitObject putBlob(InputStream contentStream) {
        return null;
    }
}
