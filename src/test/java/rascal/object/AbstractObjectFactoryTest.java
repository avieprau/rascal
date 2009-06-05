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

package rascal.object;

import rascal.AbstractTest;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public abstract class AbstractObjectFactoryTest extends AbstractTest {
    private static final int HEADER_BUFFER_LENGTH = 16;

    private static final long OBJECT_SIZE = 128;

    protected static final String OBJECT_NAME = "ab45";

    protected ReadableByteChannel readableByteChannelMock;

    protected abstract AbstractObjectFactory getObjectFactory();

    @Before
    public void setUpReadableByteChannelMock() {
        readableByteChannelMock = context.mock(ReadableByteChannel.class);
    }

    private void headerReadExpectation(final String objectType) throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(readableByteChannelMock).read(with(new TypeSafeMatcher<ByteBuffer>() {
                    public boolean matchesSafely(ByteBuffer byteBuffer) {
                        return byteBuffer.limit() == HEADER_BUFFER_LENGTH;
                    }

                    public void describeTo(Description description) {
                        description.appendText(
                                String.format("Buffer for header should be %d bytes length", HEADER_BUFFER_LENGTH));
                    }
                }));
                will(new CustomAction("writes header to buffer") {
                    public Object invoke(Invocation invocation) throws Throwable {
                        ByteBuffer buffer = (ByteBuffer) invocation.getParameter(0);
                        String headerString = String.format("%s %d", objectType, OBJECT_SIZE);
                        buffer.put(headerString.getBytes());
                        buffer.put((byte) 0);
                        return HEADER_BUFFER_LENGTH;
                    }
                });
            }
        });
    }

    @Test
    public void testGetObjectsWithDifferentTypes() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation("blob");
                headerReadExpectation("commit");
                headerReadExpectation("tag");
                headerReadExpectation("tree");
                headerReadExpectation("test");
            }
        });
        GitObject object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(GitObjectType.BLOB, object.getType());
        object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(GitObjectType.COMMIT, object.getType());
        object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(GitObjectType.TAG, object.getType());
        object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(GitObjectType.TREE, object.getType());
    }

    @Test
    public void testGetObjectWithUnknownType() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation("test");
            }
        });
        try {
            getObjectFactory().createObject(OBJECT_NAME);
            Assert.fail();
        } catch (CorruptedObjectException e) {
            if (!(e.getCause() instanceof UnknownObjectTypeException)) {
                Assert.fail();
            }
        }
    }
}
