package jgit.object;

import jgit.storage.ReadableChannelFactory;

public interface ObjectFactory {
    GitObject createObject(String name, ReadableChannelFactory channelFactory);
}
