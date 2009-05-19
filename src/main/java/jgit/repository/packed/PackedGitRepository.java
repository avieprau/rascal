package jgit.repository.packed;

import jgit.objects.GitObject;
import jgit.objects.Blob;
import jgit.repository.GitRepository;

import java.io.InputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class PackedGitRepository implements GitRepository {
    public GitObject getObject(String name) throws IOException {
        return null;
    }

    public Blob putBlob(InputStream contentStream) throws IOException {
        return null;
    }
}
