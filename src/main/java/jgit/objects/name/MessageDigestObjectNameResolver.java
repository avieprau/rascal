package jgit.objects.name;

import org.apache.commons.codec.binary.Hex;

import java.io.InputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MessageDigestObjectNameResolver implements ObjectNameResolver {
    private final int BUFFER_SIZE = 1024 * 1024 * 4;

    protected abstract String getAlgorithmName();

    protected MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getObjectHash(InputStream contentStream) throws IOException {
        MessageDigest digest = getMessageDigest();
        byte[] buffer = new byte[BUFFER_SIZE];
        int len;
        while ((len = contentStream.read(buffer)) > 0) {
            digest.update(buffer, 0, len);
        }
        return digest.digest();
    }

    public String getObjectName(InputStream contentStream) throws IOException {
        return String.valueOf(Hex.encodeHex(getObjectHash(contentStream)));
    }
}
