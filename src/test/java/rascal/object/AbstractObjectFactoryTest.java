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

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.AssertThrows;
import rascal.AbstractTest;
import rascal.AssertThrowsWithCause;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public abstract class AbstractObjectFactoryTest extends AbstractTest {
    private static final int HEADER_BUFFER_LENGTH = 32;

    protected static final String OBJECT_NAME = "597ec6d4d549facf29766a9ec00b1acb84e2f16d";

    protected ReadableByteChannel readableByteChannelMock;

    protected abstract AbstractObjectFactory getObjectFactory();

    @Before
    public void setUpReadableByteChannelMock() {
        readableByteChannelMock = context.mock(ReadableByteChannel.class);
    }

    private void headerReadExpectation(final String header) throws Exception {
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
                        buffer.put(header.getBytes());
                        buffer.put((byte) 0);
                        return HEADER_BUFFER_LENGTH;
                    }
                });

                oneOf(readableByteChannelMock).close();
            }
        });
    }

    private void headerReadExpectation(final String objectType, final String sizeString) throws Exception {
        headerReadExpectation(String.format("%s %s", objectType, sizeString));
    }

    @Test
    public void testGetObjectsWithDifferentTypes() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation("blob", "1");
                headerReadExpectation("commit", "1");
                headerReadExpectation("tag", "1");
                headerReadExpectation("tree", "1");
                headerReadExpectation("test", "1");
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
                headerReadExpectation("test", "1");
            }
        });
        new AssertThrowsWithCause(CorruptedObjectException.class, UnknownObjectTypeException.class) {
            public void test() throws Exception {
                getObjectFactory().createObject(OBJECT_NAME);
            }
        }.runTest();
    }

    @Test
    public void testGetObjectWithCorruptedHeader() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation("testtest");
            }
        });
        new AssertThrows(CorruptedObjectException.class) {
            public void test() throws Exception {
                getObjectFactory().createObject(OBJECT_NAME);
            }
        }.runTest();
    }

    @Test
    public void testGetObjectWithCorruptedSize() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation("blob", "b123");
            }
        });
        new AssertThrowsWithCause(CorruptedObjectException.class, NumberFormatException.class) {
            public void test() throws Exception {
                getObjectFactory().createObject(OBJECT_NAME);
            }
        }.runTest();
    }

    @Test
    public void testGetObjectWithDifferentSizes() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation("blob", "123");
                headerReadExpectation("blob", "1");
                headerReadExpectation("blob", "5673");
                headerReadExpectation("blob", String.valueOf(Long.MAX_VALUE));
            }
        });
        GitObject object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(123L, object.getSize());
        object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(1L, object.getSize());
        object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(5673L, object.getSize());
        object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(Long.MAX_VALUE, object.getSize());
    }


    @Test
    public void testGetObjectWithWrongSize() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation("blob", "0");
                headerReadExpectation("blob", "-1");
            }
        });
        new AssertThrows(CorruptedObjectException.class) {
            public void test() throws Exception {
                getObjectFactory().createObject(OBJECT_NAME);
            }
        }.runTest();
        new AssertThrows(CorruptedObjectException.class) {
            public void test() throws Exception {
                getObjectFactory().createObject(OBJECT_NAME);
            }
        }.runTest();
    }

    @Test
    public void testGetObjectWithDifferentNames() throws Exception {
        String name1 = "7b6e815383f9581ea2936eaee785e79f43f73307";
        String name2 = "cc1566b0552fdebdb768ba449a8d678f20be30d5";
        context.checking(new Expectations() {
            {
                headerReadExpectation("blob", "1");
                headerReadExpectation("blob", "1");
            }
        });
        GitObject object = getObjectFactory().createObject(name1);
        Assert.assertEquals(name1, object.getName());
        object = getObjectFactory().createObject(name2);
        Assert.assertEquals(name2, object.getName());
    }
}