package jgit.object.name;

public class SHA1ObjectNameResolverIntegrationTest extends AbstractMessageDigestObjectNameResolverIntegrationTest {
    @Override
    protected MessageDigestObjectNameResolver getObjectNameResolver() {
        return new SHA1ObjectNameResolver();
    }
}
