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

package rascal.object.name;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import rascal.AbstractTempFileWithRandomDataIntegrationTest;
import rascal.object.GitObjectType;
import rascal.object.source.FileChannelBlobSource;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractMessageDigestObjectNameResolverIntegrationTest
        extends AbstractTempFileWithRandomDataIntegrationTest {
    protected abstract MessageDigestObjectNameResolver getObjectNameResolver();

    protected abstract String getHash(byte[] buffer) throws NoSuchAlgorithmException;

    @Test
    public void testGetBlobName() throws Exception {
        FileChannel tempFileChannel = new FileInputStream(tempFile).getChannel();
        String objectName = getObjectNameResolver().getObjectName(new FileChannelBlobSource(tempFileChannel));
        String headerString = String.format("%s %d", GitObjectType.BLOB, testData.length);
        byte[] header = ArrayUtils.add(headerString.getBytes(), (byte) 0);
        Assert.assertEquals(getHash(ArrayUtils.addAll(header, testData)), objectName);
    }
}
