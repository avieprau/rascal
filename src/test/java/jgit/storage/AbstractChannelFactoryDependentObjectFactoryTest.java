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

package jgit.storage;

import jgit.object.AbstractObjectFactory;
import jgit.object.AbstractObjectFactoryTest;
import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractChannelFactoryDependentObjectFactoryTest extends AbstractObjectFactoryTest {
    protected ReadableChannelFactory readableChannelFactoryMock;

    protected abstract ChannelFactoryDependentObjectFactory getChannelFactoryDependentObjectFactory();

    protected AbstractObjectFactory getObjectFactory() {
        return getChannelFactoryDependentObjectFactory();
    }

    @Before
    public void setUpChannelFactoryMock() throws Exception {
        readableChannelFactoryMock = context.mock(ReadableChannelFactory.class);
        context.checking(new Expectations() {
            {
                allowing(readableChannelFactoryMock).createChannel();
                will(returnValue(readableByteChannelMock));
            }
        });
    }

    @Test
    public void testGetChannel() throws Exception {
        Assert.assertSame(readableByteChannelMock, getChannelFactoryDependentObjectFactory().getChannel(OBJECT_NAME));
    }
}
