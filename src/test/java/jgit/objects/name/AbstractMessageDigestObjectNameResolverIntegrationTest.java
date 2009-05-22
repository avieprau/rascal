package jgit.objects.name;

import jgit.AbstractTempFileWithRandomDataIntegrationTest;
import jgit.objects.source.FileChannelBlobSource;
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
        String blobName = objectNameResolver.getBlobName(new FileChannelBlobSource(tempFileChannel));
        MessageDigest digest = objectNameResolver.getMessageDigest();
        digest.update(testData);
        Assert.assertEquals(String.valueOf(Hex.encodeHex(digest.digest())), blobName);
    }
}
