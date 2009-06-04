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

import rascal.object.GitObjectType;
import org.apache.commons.lang.ArrayUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

public abstract class BlobSource implements ObjectSource {
    protected abstract long getBlobSize() throws IOException;

    protected abstract void copyBlobDataTo(WritableByteChannel destination) throws IOException;

    public final void copyTo(WritableByteChannel destination) throws IOException {
        String headerString = String.format("%s %d", GitObjectType.BLOB, getBlobSize());
        byte[] header = ArrayUtils.add(headerString.getBytes(), (byte) 0);
        destination.write(ByteBuffer.wrap(header));
        copyBlobDataTo(destination);
    }
}
