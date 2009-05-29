package jgit.object.name;

import jgit.object.source.ObjectSource;

import java.io.IOException;

public interface ObjectNameResolver {
    String getObjectName(ObjectSource source) throws IOException;
}
