package jgit.object.name;


public class SHA1ObjectNameResolver extends MessageDigestObjectNameResolver {
    private static final String SHA1_ALGORITHM_NAME = "SHA-1";

    protected String getAlgorithmName() {
        return SHA1_ALGORITHM_NAME;
    }
}
