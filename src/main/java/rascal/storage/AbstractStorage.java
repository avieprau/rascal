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

package rascal.storage;

import rascal.object.CorruptedObjectException;
import rascal.object.GitObject;
import rascal.object.ObjectFactory;
import rascal.object.name.ObjectNameResolver;
import rascal.object.source.ObjectSource;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;

public abstract class AbstractStorage implements Storage {
    private ObjectNameResolver objectNameResolver;

    protected AbstractStorage(ObjectNameResolver objectNameResolver) {
        this.objectNameResolver = objectNameResolver;
    }

    protected abstract ObjectFactory getObjectFactory();

    protected abstract WritableChannelFactory getWritableChannelFactory(String objectName);

    public GitObject getObject(String name) throws IOException, CorruptedObjectException {
        return getObjectFactory().createObject(name);
    }

    public void addObject(ObjectSource source) throws IOException {
        String objectName = objectNameResolver.getObjectName(source);
        WritableByteChannel channel = getWritableChannelFactory(objectName).createChannel();
        source.copyTo(channel);
        channel.close();
    }
}
