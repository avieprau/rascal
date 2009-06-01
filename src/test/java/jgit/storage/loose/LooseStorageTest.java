package jgit.storage.loose;

import jgit.object.ObjectFactory;
import jgit.storage.AbstractStorage;
import jgit.storage.AbstractStorageTest;
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
        looseStorage = new LooseStorage(storageLayout, objectNameResolverMock) {
            @Override
            protected ObjectFactory getObjectFactory() {
                return objectFactoryMock;
            }

            @Override
            protected WritableChannelFactory getWritableChannelFactory(String objectName) {
                return writableChannelFactoryMock;
            }
        };
    }
}
