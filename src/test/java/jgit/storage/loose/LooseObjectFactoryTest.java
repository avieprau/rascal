package jgit.storage.loose;

import jgit.storage.AbstractChannelFactoryDependentObjectFactoryTest;
import jgit.storage.ReadableChannelFactory;
import jgit.storage.ChannelFactoryDependentObjectFactory;
import org.junit.Before;

public class LooseObjectFactoryTest extends AbstractChannelFactoryDependentObjectFactoryTest {
    private LooseObjectFactory objectFactory;

    protected ChannelFactoryDependentObjectFactory getChannelFactoryDependentObjectFactory() {
        return objectFactory;
    }

    @Before
    public void setUpObjectFactory() {
        objectFactory = new LooseObjectFactory(null) {
            @Override
            protected ReadableChannelFactory getChannelFactory(String objectName) {
                return readableChannelFactoryMock;
            }
        };
    }
}
