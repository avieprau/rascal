package jgit.storage;

import jgit.AbstractIntegrationTest;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.After;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.io.FileUtils;

public class AbstractTempDirectoryIntegrationTest extends AbstractIntegrationTest {
    private static final String TEMP_DIR_NAME_PREFIX = "jgit_test_";

    private static final int TEMP_DIR_SUFFIX_LENGTH = 6;

    protected File tempDir;

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
