package jgit;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;

import java.io.FileOutputStream;

public abstract class AbstractTempFileWithRandomDataIntegrationTest extends AbstractTempFileIntegrationTest {
    private static final int TEST_DATA_SIZE = 1024;

    protected byte[] testData;

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
}
