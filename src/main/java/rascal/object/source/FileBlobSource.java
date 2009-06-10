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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class FileBlobSource extends BlobSource {
    private File source;

    public FileBlobSource(File source) {
        this.source = source;
    }

    private FileChannel createChannel() throws IOException {
        return new FileInputStream(source).getChannel();
    }

    protected long getBlobSize() throws IOException {
        FileChannel channel = createChannel();
        try {
            return channel.size();
        } finally {
            channel.close();
        }
    }

    protected void copyBlobDataTo(WritableByteChannel destination) throws IOException {
        FileChannel channel = createChannel();
        try {
            channel.transferTo(0, channel.size(), destination);
        } finally {
            channel.close();
        }
    }
}
