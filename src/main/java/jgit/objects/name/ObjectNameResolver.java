package jgit.objects.name;

import java.io.InputStream;
import java.io.IOException;

public interface ObjectNameResolver {
    String getObjectName(InputStream contentStream) throws IOException;
}
