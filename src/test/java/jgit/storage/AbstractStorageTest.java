package jgit.storage;

import jgit.AbstractTest;
import jgit.object.GitObject;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;
import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.channels.WritableByteChannel;

public abstract class AbstractStorageTest extends AbstractTest {
    protected ObjectSource sourceMock;

    protected ObjectNameResolver objectNameResolverMock;

    protected ReadableChannelFactory readableChannelFactoryMock;

    protected WritableChannelFactory writableChannelFactoryMock;

    private static final String OBJECT_NAME = "1f25";

    protected abstract AbstractStorage getStorage();

    @Before
    public void setUpMocks() throws Exception {
        objectNameResolverMock = context.mock(ObjectNameResolver.class);
        sourceMock = context.mock(ObjectSource.class);
        readableChannelFactoryMock = context.mock(ReadableChannelFactory.class);
        writableChannelFactoryMock = context.mock(WritableChannelFactory.class);
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
            }
        });
        GitObject resultObject = getStorage().addObject(sourceMock);
        Assert.assertEquals(OBJECT_NAME, resultObject.getName());
    }
}
