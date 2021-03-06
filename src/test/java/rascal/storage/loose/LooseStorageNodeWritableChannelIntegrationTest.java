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
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class LooseStorageNodeWritableChannelIntegrationTest extends AbstractLooseStorageNodeChannelIntegrationTest {
    @Test
    public void testWrite() throws IOException {
        LooseStorageNodeWritableChannel channel
                = new LooseStorageNodeWritableChannel(storageLayoutMock, storageConfiguration, objectName);
        Assert.assertTrue("Channel should be open", channel.isOpen());
        Assert.assertEquals(testData.length, channel.write(ByteBuffer.wrap(testData)));
        channel.close();
        File objectDir = new File(objectsDir, objectName.substring(0, 2)); // object dir name should be 2 chars length
        Assert.assertTrue("Object directory should exist", objectDir.isDirectory());
        File objectFile = new File(objectDir, objectName.substring(2)); // tail of object name is name for object file
        Assert.assertTrue("Object file should exist", objectFile.isFile());
        Assert.assertTrue(ArrayUtils.isEquals(FileUtils.readFileToByteArray(objectFile), deflatedTestData));
    }

    @Test
    public void testWriteWithExistingObjectDirectory() throws IOException {
        File objectDir = new File(objectsDir, objectName.substring(0, 2));
        Assert.assertTrue(objectDir.mkdir());
        LooseStorageNodeWritableChannel channel = new LooseStorageNodeWritableChannel(storageLayoutMock,
                storageConfiguration, objectName);
        channel.write(ByteBuffer.wrap(testData));
        channel.close();
    }
}
