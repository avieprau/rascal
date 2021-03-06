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

import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class LooseStorageNodeReadableChannelIntegrationTest extends AbstractLooseStorageNodeChannelIntegrationTest {
    @Before
    public void setUpObjectFile() throws IOException {
        File objectDir = new File(objectsDir, objectName.substring(0, 2));
        if (!objectDir.mkdir()) {
            throw new IOException("Can't create object dir");
        }
        File objectFile = new File(objectDir, objectName.substring(2));
        if (!objectFile.createNewFile()) {
            throw new IOException("Can't create object file");
        }
        FileOutputStream out = new FileOutputStream(objectFile);
        out.write(deflatedTestData);
        out.close();
    }

    @Test
    public void testRead() throws IOException {
        LooseStorageNodeReadableChannel channel = new LooseStorageNodeReadableChannel(storageLayoutMock, objectName);
        Assert.assertTrue(channel.isOpen());
        byte[] buffer = new byte[testData.length];
        int res = channel.read(ByteBuffer.wrap(buffer));
        Assert.assertEquals(testData.length, res);
        res = channel.read(ByteBuffer.allocate(1)); // just try read some more bytes
        Assert.assertEquals(-1, res);
        Assert.assertTrue(ArrayUtils.isEquals(testData, buffer));
        channel.close();
    }
}
