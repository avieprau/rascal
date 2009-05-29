package jgit.object.name;

import jgit.object.name.MessageDigestObjectNameResolver;
import jgit.object.name.SHA1ObjectNameResolver;

public class SHA1ObjectNameResolverIntegrationTest extends AbstractMessageDigestObjectNameResolverIntegrationTest {
    @Override
    protected MessageDigestObjectNameResolver getObjectNameResolver() {
        return new SHA1ObjectNameResolver();
    }
}
