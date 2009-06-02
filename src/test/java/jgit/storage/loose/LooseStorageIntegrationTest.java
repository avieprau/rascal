package jgit.storage.loose;

import jgit.object.name.SHA1ObjectNameResolver;
import jgit.object.source.ObjectSource;
import jgit.object.source.FileChannelBlobSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;

import java.io.*;

public class LooseStorageIntegrationTest extends AbstractLooseStorageLayoutDependentIntegrationTest {
    private static final String OBJECT_NAME = "243be0d945ba35001a4cfb3ebc4560b22c0e9e2b";

    private static final String TEST_DATA_FILE_NAME_SUFFIX = ".data";

    private static final String DEFLATED_TEST_DATA_FILE_NAME_SUFFIX = "-deflated.data";

    private byte[] testData;

    private byte[] deflatedTestData;

    private LooseStorage storage;

    @Before
    public void setUp() throws Exception {
        storage = new LooseStorage(storageLayoutMock, new SHA1ObjectNameResolver());
    }

    private byte[] loadTestDataWithNameSuffix(String nameSuffix) throws IOException {
        String resourceNamePrefix = getClass().getName().replace('.', '/');
        InputStream in = getClass().getClassLoader().getResourceAsStream(resourceNamePrefix + nameSuffix);
        byte[] data = new byte[in.available()];
        if (in.read(data) != data.length) {
            throw new IOException("Can't load test data");
        }
        return data;
    }

    @Before
    public void setUpTestData() throws Exception {
        testData = loadTestDataWithNameSuffix(TEST_DATA_FILE_NAME_SUFFIX);
        deflatedTestData = loadTestDataWithNameSuffix(DEFLATED_TEST_DATA_FILE_NAME_SUFFIX);
    }

    @Test
    public void testAddObject() throws Exception {
        File testFile = new File(tempDir, "test");
        FileOutputStream out = new FileOutputStream(testFile);
        out.write(testData);
        out.close();
        ObjectSource source = new FileChannelBlobSource(new FileInputStream(testFile).getChannel());
        storage.addObject(source);
        File objectDir = new File(objectsDir, OBJECT_NAME.substring(0, 2));
        Assert.assertTrue(objectDir.isDirectory());
        File addedTestFile = new File(objectDir, OBJECT_NAME.substring(2));
        Assert.assertTrue(addedTestFile.isFile());
        Assert.assertTrue(ArrayUtils.isEquals(deflatedTestData, FileUtils.readFileToByteArray(addedTestFile)));
    }
}
