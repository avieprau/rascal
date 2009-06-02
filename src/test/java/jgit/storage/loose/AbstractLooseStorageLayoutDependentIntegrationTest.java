package jgit.storage.loose;

import jgit.storage.AbstractTempDirectoryIntegrationTest;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

public class AbstractLooseStorageLayoutDependentIntegrationTest extends AbstractTempDirectoryIntegrationTest {
    private static final String OBJECTS_DIR_NAME = "objects";

    protected LooseStorageLayout storageLayoutMock;

    protected File objectsDir;

    @Before
    public void setUpStorageLayoutMock() {
        storageLayoutMock = new DefaultLooseStorageLayout() {
            @Override
            public File getRepositoryDir() {
                return tempDir;
            }
        };
    }

    @Before
    public void setUpObjectsDir() throws IOException {
        objectsDir = new File(tempDir, OBJECTS_DIR_NAME);
        if (!objectsDir.mkdir()) {
            throw new IOException("Can't create directory for objects");
        }
    }
}
