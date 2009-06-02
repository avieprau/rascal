package jgit.storage;

import jgit.object.AbstractObjectFactoryTest;
import jgit.object.AbstractObjectFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.jmock.Expectations;

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
