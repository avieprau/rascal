package jgit.repository;

import jgit.AbstractTest;
import jgit.objects.Blob;
import jgit.objects.name.ObjectNameResolver;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.springframework.test.util.ReflectionTestUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.ArrayUtils;
import org.jmock.Expectations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class LooseGitRepositoryTest extends AbstractTest {
    private LooseGitRepository looseGitRepository;

    private LooseObjectOutputStreamFactory outputStreamFactoryMock;

    private ObjectNameResolver objectNameResolverMock;

    private static final int TEST_DATA_SIZE = 1024;

    private static final String OBJECT_NAME = "1f25";

    private byte[] testData;

    @Before
    public void setUp() throws Exception {
        looseGitRepository = new LooseGitRepository();
        outputStreamFactoryMock = context.mock(LooseObjectOutputStreamFactory.class);
        ReflectionTestUtils.setField(looseGitRepository, "outputStreamFactory", outputStreamFactoryMock);
        objectNameResolverMock = context.mock(ObjectNameResolver.class);
        ReflectionTestUtils.setField(looseGitRepository, "objectNameResolver", objectNameResolverMock);
    }

    @Before
    public void setUpTestData() {
        testData = new byte[TEST_DATA_SIZE];
        for (int i = 0; i < testData.length; i++) {
            testData[i] = (byte) RandomUtils.nextInt(128);
        }
    }

    @Test
    public void testGetObject() {
    }

    @Test
    public void testPutBlob() throws Exception {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        context.checking(new Expectations() {
            {
                one(objectNameResolverMock).getObjectName(inputStream);
                will(returnValue(OBJECT_NAME));

                one(outputStreamFactoryMock).createOutputStream(OBJECT_NAME);
                will(returnValue(outputStream));
            }
        });
        Blob resultBlob = looseGitRepository.putBlob(inputStream);
        Assert.assertTrue(ArrayUtils.isEquals(testData, outputStream.toByteArray()));
        Assert.assertEquals(OBJECT_NAME, resultBlob.getName());
    }
}
