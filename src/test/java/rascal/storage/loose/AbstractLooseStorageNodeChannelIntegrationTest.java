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

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import rascal.RandomTestDataUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public abstract class AbstractLooseStorageNodeChannelIntegrationTest
        extends AbstractLooseStorageConfigurationDependentIntegrationTest {
    private static final String OBJECT_NAME_CHARS = "0123456789abcdef";

    private static final int OBJECT_NAME_LENGTH = 40;

    private static final int DEFLATED_DATA_COMPRESS_LEVER = 1;

    protected byte[] testData;

    protected byte[] deflatedTestData;

    protected String objectName;

    @Before
    public void setUpObjectName() {
        objectName = RandomStringUtils.random(OBJECT_NAME_LENGTH, OBJECT_NAME_CHARS);
    }

    @Before
    public void setUpTestData() throws IOException {
        testData = RandomTestDataUtils.createRandomData();
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        DeflaterOutputStream out = new DeflaterOutputStream(outBuffer, new Deflater(DEFLATED_DATA_COMPRESS_LEVER));
        out.write(testData);
        out.close();
        deflatedTestData = outBuffer.toByteArray();
    }
}
