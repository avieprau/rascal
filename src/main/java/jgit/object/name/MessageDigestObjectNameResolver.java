package jgit.object.name;

import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.channels.WritableByteChannel;
import java.nio.ByteBuffer;

import jgit.object.source.ObjectSource;

public abstract class MessageDigestObjectNameResolver implements ObjectNameResolver {
    protected abstract String getAlgorithmName();

    protected MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getObjectHash(ObjectSource source) throws IOException {
        final MessageDigest digest = getMessageDigest();
        source.copyTo(new WritableByteChannel() {
            public int write(ByteBuffer src) throws IOException {
                int n = src.remaining();
                digest.update(src);
                return n;
            }

            public boolean isOpen() {
                return true;
            }

            public void close() throws IOException {
            }
        });
        return digest.digest();
    }

    public String getObjectName(ObjectSource source) throws IOException {
        return String.valueOf(Hex.encodeHex(getObjectHash(source)));
    }
}
