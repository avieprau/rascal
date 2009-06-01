package jgit.storage.loose;

import jgit.AbstractIntegrationTest;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.After;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.io.FileUtils;

public abstract class AbstractLooseStorageNodeChannelIntegrationTest extends AbstractIntegrationTest {
    private static final String TEMP_DIR_NAME_PREFIX = "jgit_test_";

    private static final int TEMP_DIR_SUFFIX_LENGTH = 6;

    private static final int TEST_DATA_LENGTH = 1024;

    private static final String OBJECT_NAME_CHARS = "0123456789abcdef";

    private static final int OBJECT_NAME_LENGTH = 40;

    protected File tempDir;

    protected byte[] testData;

    protected String objectName;

    protected class LooseStorageLayoutMock extends DefaultLooseStorageLayout {
        @Override
        public File getObjectsDir() {
            return tempDir;
        }
    }

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
}
