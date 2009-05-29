package jgit.object.name;

import jgit.AbstractTempFileWithRandomDataIntegrationTest;
import jgit.object.source.FileChannelBlobSource;
import jgit.object.GitObjectType;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public abstract class AbstractMessageDigestObjectNameResolverIntegrationTest
        extends AbstractTempFileWithRandomDataIntegrationTest {
    protected abstract MessageDigestObjectNameResolver getObjectNameResolver();

    private MessageDigestObjectNameResolver objectNameResolver;

    @Before
    public void setUp() {
        objectNameResolver = getObjectNameResolver();
    }

    @Test
    public void testGetBlobName() throws Exception {
        FileChannel tempFileChannel = new FileInputStream(tempFile).getChannel();
        String objectName = objectNameResolver.getObjectName(new FileChannelBlobSource(tempFileChannel));
        MessageDigest digest = objectNameResolver.getMessageDigest();
        digest.update(String.format("%s %d", GitObjectType.BLOB, testData.length).getBytes());
        digest.update((byte) 0);
        digest.update(testData);
        Assert.assertEquals(String.valueOf(Hex.encodeHex(digest.digest())), objectName);
    }
}
