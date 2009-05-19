package jgit.objects.name;

import jgit.objects.name.MessageDigestObjectNameResolver;
import jgit.objects.name.SHA1ObjectNameResolver;

public class SHA1ObjectNameResolverIntegrationTest extends AbstractMessageDigestObjectNameResolverIntegrationTest {
    @Override
    protected MessageDigestObjectNameResolver getObjectNameResolver() {
        return new SHA1ObjectNameResolver();
    }
}
