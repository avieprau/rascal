package jgit.repository;

import java.io.InputStream;

public interface LooseObjectInputStreamFactory {
    InputStream createInputStream(String objectName);
}
