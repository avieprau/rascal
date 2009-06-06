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

import org.apache.commons.codec.binary.Hex;
import rascal.object.source.ObjectSource;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MessageDigestObjectNameResolver implements ObjectNameResolver {
    protected abstract String getAlgorithmName();

    protected MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(getAlgorithmName());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getObjectHash(ObjectSource source) throws IOException {
        final MessageDigest digest = getMessageDigest();
        source.copyTo(new WritableByteChannel() {
            public int write(ByteBuffer src) throws IOException {
                int n = src.remaining();
                digest.update(src);
                return n;
            }

            public boolean isOpen() {
                return true;
            }

            public void close() throws IOException {
            }
        });
        return digest.digest();
    }

    public String getObjectName(ObjectSource source) throws IOException {
        return String.valueOf(Hex.encodeHex(getObjectHash(source)));
    }
}
