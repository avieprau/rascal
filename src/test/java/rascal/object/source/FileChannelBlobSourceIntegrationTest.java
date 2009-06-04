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

package rascal.object.source;

import rascal.AbstractTempFileWithRandomDataIntegrationTest;
import rascal.object.GitObjectType;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.channels.Channels;

public class FileChannelBlobSourceIntegrationTest extends AbstractTempFileWithRandomDataIntegrationTest {
    private FileChannelBlobSource fileChannelBlobSource;

    @Before
    public void setUp() throws Exception {
        FileInputStream inputStream = new FileInputStream(tempFile);
        fileChannelBlobSource = new FileChannelBlobSource(inputStream.getChannel());
    }

    @Test
    public void testCopyTo() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        fileChannelBlobSource.copyTo(Channels.newChannel(output));
        byte[] buffer = output.toByteArray();
        String expectedHeaderString = String.format("%s %d", GitObjectType.BLOB, testData.length);
        byte[] expectedHeader = ArrayUtils.add(expectedHeaderString.getBytes(), (byte) 0);
        byte[] header = ArrayUtils.subarray(buffer, 0, expectedHeader.length);
        Assert.assertTrue("Header should be of format \"type(space)size(null byte)\"",
                ArrayUtils.isEquals(expectedHeader, header));
        byte[] content = ArrayUtils.subarray(buffer, expectedHeader.length, buffer.length);
        Assert.assertTrue(ArrayUtils.isEquals(testData, content));
    }
}
