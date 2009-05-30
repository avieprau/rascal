package jgit.storage.loose;

import jgit.AbstractIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class LooseGitObjectWritableByteChannelTest extends AbstractIntegrationTest {
    private static final String TEMP_DIR_NAME_PREFIX = "jgit_test_";

    private static final int TEMP_DIR_SUFFIX_LENGTH = 6;

    private static final int TEST_DATA_LENGTH = 1024;

    private File tempDir;

    private byte[] testData;

    private static final String OBJECT_NAME_CHARS = "0123456789abcdef";

    private static final int OBJECT_NAME_LENGTH = 40;

    private String objectName;

    @Before
    public void setUpObjectName() {
        objectName = RandomStringUtils.random(OBJECT_NAME_LENGTH, OBJECT_NAME_CHARS);
    }

    @Before
    public void setUpTestData() throws IOException {
        testData = new byte[TEST_DATA_LENGTH];
        for (int i = 0; i < testData.length; i++) {
            testData[i] = (byte) RandomUtils.nextInt(Byte.MAX_VALUE);
        }
    }

    @Before
    public void setUpTempDirectory() throws IOException {
        File tempDirRoot = new File(System.getProperty("java.io.tmpdir"));
        tempDir = new File(tempDirRoot, TEMP_DIR_NAME_PREFIX
                + RandomStringUtils.randomNumeric(TEMP_DIR_SUFFIX_LENGTH));
        if (!tempDir.mkdir()) {
            throw new IOException("Can't create temprorary directory for testing");
        }
    }

    @After
    public void tearDownTempDirectory() throws IOException {
        FileUtils.deleteDirectory(tempDir);
    }

    @Test
    public void testWriteToCannel() throws IOException {
        LooseGitObjectWritableByteChannel channel
                = new LooseGitObjectWritableByteChannel(new DefaultLooseStorageLayout() {
            @Override
            public File getObjectsDir() {
                return tempDir;
            }
        }, objectName);
        Assert.assertTrue("Channel should be open", channel.isOpen());
        Assert.assertEquals(testData.length, channel.write(ByteBuffer.wrap(testData)));
        channel.close();
        File objectDir = new File(tempDir, objectName.substring(0, 2));
        Assert.assertTrue("Object directory should exist", objectDir.isDirectory());
        File objectFile = new File(objectDir, objectName.substring(2));
        Assert.assertTrue("Object file should exist", objectFile.isFile());
        Assert.assertTrue(ArrayUtils.isEquals(FileUtils.readFileToByteArray(objectFile), testData));
    }
}
