package jgit.storage;

import jgit.AbstractTest;
import jgit.object.GitObject;
import jgit.object.storage.GitObjectStorageNode;
import jgit.object.name.ObjectNameResolver;
import jgit.object.source.ObjectSource;
import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.channels.WritableByteChannel;

public abstract class AbstractGitStorageTest extends AbstractTest {
    private AbstractGitStorage gitStorage;

    private ObjectSource sourceMock;

    private GitObjectStorageNode objectStorageNodeMock;

    private ObjectNameResolver objectNameResolverMock;

    private static final String OBJECT_NAME = "1f25";

    protected abstract AbstractGitStorage getGitStorage();

    @Before
    public void setUp() throws Exception {
        gitStorage = getGitStorage();
        objectStorageNodeMock = context.mock(GitObjectStorageNode.class);
        ReflectionTestUtils.setField(gitStorage, "objectStorageNode", objectStorageNodeMock);
        objectNameResolverMock = context.mock(ObjectNameResolver.class);
        ReflectionTestUtils.setField(gitStorage, "objectNameResolver", objectNameResolverMock);
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
        GitObject resultObject = gitStorage.addObject(sourceMock);
        Assert.assertEquals(OBJECT_NAME, resultObject.getName());
    }
}
