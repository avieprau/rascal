package jgit.repository.loose;

import java.io.InputStream;

public interface LooseObjectInputStreamFactory {
    InputStream createInputStream(String objectName);
}
