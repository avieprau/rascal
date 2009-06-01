package jgit.object;

import jgit.storage.ReadableChannelFactory;

public class DefaultObjectFactory implements ObjectFactory {
    public GitObject createObject(String name, ReadableChannelFactory channelFactory) {
        return new GitObject(name, channelFactory);
    }
}
