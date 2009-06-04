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
import rascal.storage.AbstractStorage;
import rascal.storage.AbstractStorageTest;
import rascal.storage.WritableChannelFactory;
import org.junit.Before;

public class LooseStorageTest extends AbstractStorageTest {
    private LooseStorage looseStorage;

    protected AbstractStorage getStorage() {
        return looseStorage;
    }

    @Before
    public void setUpStorage() {
        LooseStorageLayout storageLayout = context.mock(LooseStorageLayout.class);
        looseStorage = new LooseStorage(storageLayout, objectNameResolverMock) {
            @Override
            protected ObjectFactory getObjectFactory() {
                return objectFactoryMock;
            }

            @Override
            protected WritableChannelFactory getWritableChannelFactory(String objectName) {
                return writableChannelFactoryMock;
            }
        };
    }
}
