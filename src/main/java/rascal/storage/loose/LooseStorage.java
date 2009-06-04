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

package rascal.storage.loose;

import rascal.object.ObjectFactory;
import rascal.object.name.ObjectNameResolver;
import rascal.storage.AbstractStorage;
import rascal.storage.WritableChannelFactory;

public class LooseStorage extends AbstractStorage {
    private LooseStorageLayout storageLayout;

    private ObjectFactory objectFactory;

    public LooseStorage(LooseStorageLayout storageLayout, ObjectNameResolver objectNameResolver) {
        super(objectNameResolver);
        this.storageLayout = storageLayout;
        objectFactory = new LooseObjectFactory(storageLayout);
    }

    protected ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    protected WritableChannelFactory getWritableChannelFactory(String objectName) {
        return new LooseStorageNodeWritableChannelFactory(storageLayout, objectName);
    }
}
