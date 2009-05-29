package jgit.storage;

import jgit.object.GitObject;
import jgit.object.source.ObjectSource;

import java.io.IOException;

public interface GitStorage {
    GitObject getObject(String name) throws IOException;

    GitObject addObject(ObjectSource source) throws IOException;
}
