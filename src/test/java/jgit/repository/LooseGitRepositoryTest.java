package jgit.repository;

import jgit.AbstractTest;
import org.junit.Test;
import org.junit.Before;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.MessageDigest;

public class LooseGitRepositoryTest extends AbstractTest {
    private LooseGitRepository looseGitRepository;

    private LooseObjectOutputStreamFactory outputStreamFactoryMock;

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(looseGitRepository, "sha1MessageDigest", MessageDigest.getInstance("SHA-1"));
        outputStreamFactoryMock = context.mock(LooseObjectOutputStreamFactory.class);
    }

    @Test
    public void testGetObject() {
    }

    @Test
    public void testPutBlob() {

    }
}
