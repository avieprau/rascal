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

package jgit.object.name;

import jgit.AbstractTempFileWithRandomDataIntegrationTest;
import jgit.object.GitObjectType;
import jgit.object.source.FileChannelBlobSource;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public abstract class AbstractMessageDigestObjectNameResolverIntegrationTest
        extends AbstractTempFileWithRandomDataIntegrationTest {
    protected abstract MessageDigestObjectNameResolver getObjectNameResolver();

    private MessageDigestObjectNameResolver objectNameResolver;

    @Before
    public void setUp() {
        objectNameResolver = getObjectNameResolver();
    }

    @Test
    public void testGetBlobName() throws Exception {
        FileChannel tempFileChannel = new FileInputStream(tempFile).getChannel();
        String objectName = objectNameResolver.getObjectName(new FileChannelBlobSource(tempFileChannel));
        MessageDigest digest = objectNameResolver.getMessageDigest();
        digest.update(String.format("%s %d", GitObjectType.BLOB, testData.length).getBytes());
        digest.update((byte) 0);
        digest.update(testData);
        Assert.assertEquals(String.valueOf(Hex.encodeHex(digest.digest())), objectName);
    }
}
