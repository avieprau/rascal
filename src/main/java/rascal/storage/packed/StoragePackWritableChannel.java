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

package rascal.storage.packed;

import java.nio.channels.WritableByteChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

class StoragePackWritableChannel implements WritableByteChannel {
    public int write(ByteBuffer src) throws IOException {
        return 0;
    }

    public boolean isOpen() {
        return false;
    }

    public void close() throws IOException {

    }
}
