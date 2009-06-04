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

package rascal;

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
