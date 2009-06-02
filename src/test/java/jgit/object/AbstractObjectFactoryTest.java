package jgit.object;

import jgit.AbstractTest;

import java.nio.channels.ReadableByteChannel;
import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

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

    private void headerReadExpectation() throws Exception {
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
                        String headerString = String.format("%s %d", "blob", OBJECT_SIZE);
                        buffer.put(headerString.getBytes());
                        buffer.put((byte) 0);
                        return HEADER_BUFFER_LENGTH;
                    }
                });
            }
        });
    }

    @Test
    public void testGetObject() throws Exception {
        context.checking(new Expectations() {
            {
                headerReadExpectation();
            }
        });
        GitObject object = getObjectFactory().createObject(OBJECT_NAME);
        Assert.assertEquals(OBJECT_NAME, object.getName());
        Assert.assertEquals(GitObjectType.BLOB, object.getType());
        Assert.assertEquals(OBJECT_SIZE, object.getSize());
    }
}
