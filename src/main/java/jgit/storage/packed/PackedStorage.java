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

package jgit.storage.packed;

import jgit.object.ObjectFactory;
import jgit.object.name.ObjectNameResolver;
import jgit.storage.AbstractStorage;
import jgit.storage.WritableChannelFactory;


public class PackedStorage extends AbstractStorage {
    public PackedStorage(ObjectNameResolver objectNameResolver) {
        super(objectNameResolver);
    }

    protected ObjectFactory getObjectFactory() {
        return null;
    }

    protected WritableChannelFactory getWritableChannelFactory(String objectName) {
        return null;
    }
}
