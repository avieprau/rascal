package jgit.repository.packed;

import jgit.objects.GitObject;
import jgit.objects.Blob;
import jgit.objects.source.BlobSource;
import jgit.repository.GitRepository;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class PackedGitRepository implements GitRepository {
    public GitObject getObject(String name) throws IOException {
        return null;
    }

    public Blob addBlob(BlobSource source) throws IOException {
        return null;
    }
}
