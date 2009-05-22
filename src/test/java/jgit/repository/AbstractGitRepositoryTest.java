package jgit.repository;

import jgit.AbstractTest;
import jgit.objects.Blob;
import jgit.objects.name.ObjectNameResolver;
import jgit.objects.source.BlobSource;
import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.channels.WritableByteChannel;

public abstract class AbstractGitRepositoryTest extends AbstractTest {
    private AbstractGitRepository gitRepository;

    private BlobSource sourceMock;

    private GitObjectStorage objectStorageMock;

    private ObjectNameResolver objectNameResolverMock;

    private static final String OBJECT_NAME = "1f25";

    protected abstract AbstractGitRepository getGitRepository();

    @Before
    public void setUp() throws Exception {
        gitRepository = getGitRepository();
        objectStorageMock = context.mock(GitObjectStorage.class);
        ReflectionTestUtils.setField(gitRepository, "objectStorage", objectStorageMock);
        objectNameResolverMock = context.mock(ObjectNameResolver.class);
        ReflectionTestUtils.setField(gitRepository, "objectNameResolver", objectNameResolverMock);
        sourceMock = context.mock(BlobSource.class);
    }

    @Test
    public void testPutBlob() throws Exception {
        final WritableByteChannel storageChannelMock = context.mock(WritableByteChannel.class);
        context.checking(new Expectations() {
            {
                oneOf(objectNameResolverMock).getBlobName(sourceMock);
                will(returnValue(OBJECT_NAME));

                oneOf(objectStorageMock).getWritableChannel(OBJECT_NAME);
                will(returnValue(storageChannelMock));

                oneOf(sourceMock).copyTo(storageChannelMock);
            }
        });
        Blob resultBlob = gitRepository.addBlob(sourceMock);
        Assert.assertEquals(OBJECT_NAME, resultBlob.getName());
    }
}
