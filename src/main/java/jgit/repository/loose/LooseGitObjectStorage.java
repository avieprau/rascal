package jgit.repository.loose;

import jgit.repository.GitObjectStorage;

import java.nio.channels.WritableByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.io.IOException;

public class LooseGitObjectStorage implements GitObjectStorage {
    public WritableByteChannel getWritableChannel(String objectName) throws IOException {
        return null;
    }

    public ReadableByteChannel getReadableChannel(String objectName) throws IOException {
        return null;
    }
}
