package jgit.storage;

import jgit.object.CorruptedObjectException;
import jgit.object.GitObject;
import jgit.object.source.ObjectSource;

import java.io.IOException;

public interface Storage {
    GitObject getObject(String name) throws IOException, CorruptedObjectException;

    void addObject(ObjectSource source) throws IOException;
}
