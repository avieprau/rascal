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

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import rascal.AbstractTest;
import rascal.object.ObjectFactory;
import rascal.object.name.ObjectNameResolver;
import rascal.object.source.ObjectSource;

import java.nio.channels.WritableByteChannel;

public abstract class AbstractStorageTest extends AbstractTest {
    protected ObjectSource sourceMock;

    protected ObjectNameResolver objectNameResolverMock;

    protected ReadableChannelFactory readableChannelFactoryMock;

    protected WritableChannelFactory writableChannelFactoryMock;

    protected ObjectFactory objectFactoryMock;

    private static final String OBJECT_NAME = "1f25";

    protected abstract AbstractStorage getStorage();

    @Before
    public void setUpMocks() throws Exception {
        objectNameResolverMock = context.mock(ObjectNameResolver.class);
        sourceMock = context.mock(ObjectSource.class);
        readableChannelFactoryMock = context.mock(ReadableChannelFactory.class);
        writableChannelFactoryMock = context.mock(WritableChannelFactory.class);
        objectFactoryMock = context.mock(ObjectFactory.class);
    }

    @Test
    public void testGetObject() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(objectFactoryMock).createObject(OBJECT_NAME);
            }
        });
        getStorage().getObject(OBJECT_NAME);
    }

    @Test
    public void testAddObject() throws Exception {
        final WritableByteChannel storageChannelMock = context.mock(WritableByteChannel.class);
        context.checking(new Expectations() {
            {
                oneOf(objectNameResolverMock).getObjectName(sourceMock);
                will(returnValue(OBJECT_NAME));

                oneOf(writableChannelFactoryMock).createChannel();
                will(returnValue(storageChannelMock));

                oneOf(sourceMock).copyTo(storageChannelMock);

                oneOf(objectFactoryMock).createObject(OBJECT_NAME);
            }
        });
        getStorage().addObject(sourceMock);
    }
}
