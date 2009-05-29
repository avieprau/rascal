package jgit.storage;

import jgit.AbstractTest;
import jgit.object.GitObject;
import jgit.object.storage.ObjectStorageNode;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;
import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.channels.WritableByteChannel;

public abstract class AbstractStorageTest extends AbstractTest {
    private AbstractStorage storage;

    private ObjectSource sourceMock;

    private ObjectStorageNode objectStorageNodeMock;

    private ObjectNameResolver objectNameResolverMock;

    private static final String OBJECT_NAME = "1f25";

    protected abstract AbstractStorage getStorage();

    @Before
    public void setUp() throws Exception {
        storage = getStorage();
        objectStorageNodeMock = context.mock(ObjectStorageNode.class);
        ReflectionTestUtils.setField(storage, "objectStorageNode", objectStorageNodeMock);
        objectNameResolverMock = context.mock(ObjectNameResolver.class);
        ReflectionTestUtils.setField(storage, "objectNameResolver", objectNameResolverMock);
        sourceMock = context.mock(ObjectSource.class);
    }

    @Test
    public void testAddObject() throws Exception {
        final WritableByteChannel storageChannelMock = context.mock(WritableByteChannel.class);
        context.checking(new Expectations() {
            {
                oneOf(objectNameResolverMock).getObjectName(sourceMock);
                will(returnValue(OBJECT_NAME));

                oneOf(objectStorageNodeMock).getWritableChannel(OBJECT_NAME);
                will(returnValue(storageChannelMock));

                oneOf(sourceMock).copyTo(storageChannelMock);
            }
        });
        GitObject resultObject = storage.addObject(sourceMock);
        Assert.assertEquals(OBJECT_NAME, resultObject.getName());
    }
}
