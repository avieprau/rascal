package jgit.objects.name;

import jgit.AbstractTempFileIntegrationTest;
import jgit.objects.source.FileChannelBlobSource;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public abstract class AbstractMessageDigestObjectNameResolverIntegrationTest extends AbstractTempFileIntegrationTest {
    private static final int TEST_DATA_SIZE = 1024 * 1024 * 7;

    protected abstract MessageDigestObjectNameResolver getObjectNameResolver();

    private MessageDigestObjectNameResolver objectNameResolver;

    private byte[] testData;

    @Before
    public void setUp() {
        objectNameResolver = getObjectNameResolver();
    }

    @Before
    @Override
    public void setUpTempFile() throws Exception {
        super.setUpTempFile();
        testData = new byte[TEST_DATA_SIZE];
        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            testData[i] = (byte) RandomUtils.nextInt(128);
        }
        FileOutputStream tempFileStream = new FileOutputStream(tempFile);
        tempFileStream.write(testData);
        tempFileStream.close();
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
