package jgit.storage;

import jgit.object.AbstractObjectFactory;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;

public abstract class ChannelFactoryDependentObjectFactory extends AbstractObjectFactory {
    protected abstract ReadableChannelFactory getChannelFactory(String objectName);

    protected ReadableByteChannel getChannel(String objectName) throws IOException {
        return getChannelFactory(objectName).createChannel();
    }
}
