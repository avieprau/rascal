package jgit.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.lang.math.RandomUtils;
import jgit.AbstractIntegrationTestCase;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;

public abstract class AbstractMessageDigestObjectNameResolverIntegrationTestCase extends AbstractIntegrationTestCase {
    private static final int TEST_DATA_SIZE = 1024 * 1024 * 7;

    protected abstract MessageDigestObjectNameResolver getObjectNameResolver();

    private MessageDigestObjectNameResolver objectNameResolver;

    private byte[] testData;

    @Before
    public void setUp() {
        objectNameResolver = getObjectNameResolver();
    }

    @Before
    public void setUpTestData() {
        testData = new byte[TEST_DATA_SIZE];
        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            testData[i] = (byte) RandomUtils.nextInt(128);
        }
    }

    @Test
    public void testGetObjectName() throws Exception {
        InputStream inputStream = new ByteArrayInputStream(testData);
        MessageDigest digest = objectNameResolver.getMessageDigest();
        String objectName = objectNameResolver.getObjectName(inputStream);
        digest.update(testData);
        Assert.assertEquals(String.valueOf(digest.digest()), objectName);
    }
}
