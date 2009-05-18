package jgit.repository;

import org.springframework.stereotype.Component;

import java.io.OutputStream;

public interface LooseObjectOutputStreamFactory {
    OutputStream createOutputStream(String objectName);
}
