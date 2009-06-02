package jgit.storage.loose;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;

public abstract class AbstractLooseStorageNodeChannelIntegrationTest
        extends AbstractLooseStorageLayoutDependentIntegrationTest {
    private static final int TEST_DATA_LENGTH = 1024;

    private static final String OBJECT_NAME_CHARS = "0123456789abcdef";

    private static final int OBJECT_NAME_LENGTH = 40;

    protected byte[] testData;

    protected byte[] deflatedTestData;

    protected String objectName;

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
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        DeflaterOutputStream out = new DeflaterOutputStream(outBuffer);
        out.write(testData);
        out.close();
        deflatedTestData = outBuffer.toByteArray();
    }
}
