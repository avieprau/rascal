package jgit.storage.loose;

import jgit.storage.AbstractStorage;
import jgit.storage.AbstractStorageTest;
import jgit.storage.ReadableChannelFactory;
import jgit.storage.WritableChannelFactory;
import org.junit.Before;

public class LooseStorageTest extends AbstractStorageTest {
    private LooseStorage looseStorage;

    protected AbstractStorage getStorage() {
        return looseStorage;
    }

    @Before
    public void setUpStorage() {
        LooseStorageLayout storageLayout = context.mock(LooseStorageLayout.class);
        looseStorage = new LooseStorage(storageLayout, objectNameResolverMock, objectFactoryMock) {
            @Override
            protected ReadableChannelFactory getReadableChannelFactory(String objectName) {
                return readableChannelFactoryMock;
            }

            @Override
            protected WritableChannelFactory getWritableChannelFactory(String objectName) {
                return writableChannelFactoryMock;
            }
        };
    }
}
