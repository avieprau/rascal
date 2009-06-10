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

package rascal.object;

import org.apache.commons.lang.ArrayUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public abstract class AbstractObjectFactory implements ObjectFactory {
    private static final int OBJECT_HEADER_BUFFER_LENGTH = 32;

    protected abstract ReadableByteChannel getChannel(String objectName) throws IOException;

    private GitObjectType parseObjectType(byte[] buffer) throws UnknownObjectTypeException {
        String typeName = new String(buffer);
        if (GitObjectType.BLOB.toString().equals(typeName)) {
            return GitObjectType.BLOB;
        } else if (GitObjectType.TREE.toString().equals(typeName)) {
            return GitObjectType.TREE;
        } else if (GitObjectType.COMMIT.toString().equals(typeName)) {
            return GitObjectType.COMMIT;
        } else if (GitObjectType.TAG.toString().equals(typeName)) {
            return GitObjectType.TAG;
        }
        throw new UnknownObjectTypeException(typeName);
    }

    private long parseObjectSize(byte[] buffer) throws NumberFormatException {
        String sizeString = new String(buffer);
        return Long.valueOf(sizeString);
    }

    private GitObject createObject(final String name, byte[] buffer, int headerSpaceIndex, int headerEndIndex)
            throws CorruptedObjectException, UnknownObjectTypeException, NumberFormatException {
        GitObjectType type = parseObjectType(ArrayUtils.subarray(buffer, 0, headerSpaceIndex));
        long size = parseObjectSize(ArrayUtils.subarray(buffer, headerSpaceIndex + 1, headerEndIndex));
        if (size <= 0) {
            throw new CorruptedObjectException(name, "Wrong object size");
        }
        return new GitObject(name, type, size) {
            public ReadableByteChannel getContentChannel() throws IOException {
                return AbstractObjectFactory.this.getChannel(name);
            }
        };
    }

    public GitObject createObject(String name) throws IOException, CorruptedObjectException {
        ReadableByteChannel channel = getChannel(name);
        byte[] buffer = new byte[OBJECT_HEADER_BUFFER_LENGTH];
        channel.read(ByteBuffer.wrap(buffer));
        channel.close();
        int headerSpaceIndex;
        int headerEndIndex;
        if ((headerSpaceIndex = ArrayUtils.indexOf(buffer, (byte) ' ')) == ArrayUtils.INDEX_NOT_FOUND
                || (headerEndIndex = ArrayUtils.indexOf(buffer, (byte) 0)) == ArrayUtils.INDEX_NOT_FOUND
                || headerSpaceIndex >= headerEndIndex) {
            throw new CorruptedObjectException(name, "Corrupted object header");
        }
        try {
            return createObject(name, buffer, headerSpaceIndex, headerEndIndex);
        } catch (UnknownObjectTypeException e) {
            throw new CorruptedObjectException(name, e);
        } catch (NumberFormatException e) {
            throw new CorruptedObjectException(name, e);
        }
    }
}
