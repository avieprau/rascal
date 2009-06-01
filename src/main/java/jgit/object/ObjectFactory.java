package jgit.object;

import java.io.IOException;

public interface ObjectFactory {
    GitObject createObject(String name) throws IOException, CorruptedObjectException;
}
