package jgit;

import org.junit.After;
import org.junit.Before;

import java.io.File;

public abstract class AbstractTempFileIntegrationTest extends AbstractIntegrationTest {
    private static final String TEMP_FILE_PREFIX = "jgit_test";

    private static final String TEMP_FILE_SUFFIX = ".tmp";
    protected File tempFile;

    @Before
    public void setUpTempFile() throws Exception {
        tempFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    @After
    public void tearDownTempFile() throws Exception {
        tempFile.delete();
    }
}
