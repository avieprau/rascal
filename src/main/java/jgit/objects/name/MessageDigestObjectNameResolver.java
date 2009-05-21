package jgit.objects.name;

import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.channels.WritableByteChannel;
import java.nio.ByteBuffer;

import jgit.objects.source.BlobSource;

public abstract class MessageDigestObjectNameResolver implements ObjectNameResolver {
    protected abstract String getAlgorithmName();

    protected MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getBlobHash(BlobSource source) throws IOException {
        final MessageDigest digest = getMessageDigest();
        source.copyTo(new WritableByteChannel() {
            public int write(ByteBuffer src) throws IOException {
                byte[] bytes;
                if (src.hasArray()) {
                    bytes = src.array();
                } else {
                    bytes = new byte[src.remaining()];
                    src.get(bytes);
                }
                digest.update(bytes);
                return bytes.length;
            }

            public boolean isOpen() {
                return true;
            }

            public void close() throws IOException {
            }
        });
        return digest.digest();
    }

    public String getBlobName(BlobSource source) throws IOException {
        return String.valueOf(Hex.encodeHex(getBlobHash(source)));
    }
}
