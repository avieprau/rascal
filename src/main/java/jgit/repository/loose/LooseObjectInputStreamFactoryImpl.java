package jgit.repository.loose;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class LooseObjectInputStreamFactoryImpl implements LooseObjectInputStreamFactory {
    public InputStream createInputStream(String objectName) {
        return null;
    }
}
