package jgit.repository;

import jgit.objects.GitObject;
import jgit.objects.Blob;
import jgit.objects.name.ObjectNameResolver;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class LooseGitRepository implements GitRepository {
    private static final int BUFFER_SIZE = 1024 * 1024 * 4;

    @Autowired
    private ObjectNameResolver objectNameResolver;

    @Autowired
    private LooseObjectOutputStreamFactory outputStreamFactory;

    public GitObject getObject(String name) throws IOException {
        return null;
    }

    public Blob putBlob(InputStream contentStream) throws IOException {
        String objectName = objectNameResolver.getObjectName(contentStream);
        OutputStream outputStream = outputStreamFactory.createOutputStream(objectName);
        byte[] buffer = new byte[BUFFER_SIZE];
        int len;
        while ((len = contentStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        // TODO: blob creation
        return new Blob(objectName);
    }
}
