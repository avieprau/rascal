/*
 * Copyright 2009, Andrej Viepraŭ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rascal.storage.loose;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rascal.object.name.SHA1ObjectNameResolver;
import rascal.object.source.FileChannelBlobSource;
import rascal.object.source.ObjectSource;

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
