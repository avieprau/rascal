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
import java.util.List;
import java.util.ArrayList;

public abstract class AbstractStorageTest extends AbstractTest {
    protected ObjectNameResolver objectNameResolverMock;

    protected ReadableChannelFactory readableChannelFactoryMock;

    protected WritableChannelFactory writableChannelFactoryMock;

    protected ObjectFactory objectFactoryMock;

    private static final String OBJECT_NAME = "1f25";

    protected abstract AbstractStorage getStorage();

    @Before
    public void setUpMocks() throws Exception {
        objectNameResolverMock = context.mock(ObjectNameResolver.class);
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

    private void addObjectExpectation(final ObjectSource sourceMock,
                                      final WritableByteChannel storageChannelMock) throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(objectNameResolverMock).getObjectName(sourceMock);
                will(returnValue(OBJECT_NAME));

                oneOf(writableChannelFactoryMock).createChannel();
                will(returnValue(storageChannelMock));

                oneOf(sourceMock).copyTo(storageChannelMock);

                oneOf(storageChannelMock).close();
            }
        });

    }

    @Test
    public void testAddObject() throws Exception {
        final WritableByteChannel storageChannelMock = context.mock(WritableByteChannel.class);
        final ObjectSource sourceMock = context.mock(ObjectSource.class);
        context.checking(new Expectations() {
            {
                addObjectExpectation(sourceMock, storageChannelMock);
            }
        });
        getStorage().addObject(sourceMock);
    }

    @Test
    public void testAddObjects() throws Exception {
        final int OBJECTS_NUMBER = 5;
        final List<ObjectSource> sourceMocks = new ArrayList<ObjectSource>();
        for (int i = 0; i < OBJECTS_NUMBER; i++) {
            sourceMocks.add(context.mock(ObjectSource.class, "objectSource" + i));
        }
        context.checking(new Expectations() {
            {
                for (int i = 0; i < sourceMocks.size(); i++) {
                    ObjectSource sourceMock = sourceMocks.get(i);
                    addObjectExpectation(sourceMock, context.mock(WritableByteChannel.class, "storageChannelMock" + i));
                }
            }
        });
        getStorage().addObjects(sourceMocks);
    }
}
