package jgit.repository.loose;

import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class LooseObjectOutputStreamFactoryImpl implements LooseObjectOutputStreamFactory {
    public OutputStream createOutputStream(String objectName) {
        return null;
    }
}
