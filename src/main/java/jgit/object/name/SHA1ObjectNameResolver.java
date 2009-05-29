package jgit.object.name;

import org.springframework.stereotype.Component;

@Component
public class SHA1ObjectNameResolver extends MessageDigestObjectNameResolver {
    private static final String SHA1_ALGORITHM_NAME = "SHA-1";

    @Override
    protected String getAlgorithmName() {
        return SHA1_ALGORITHM_NAME;
    }
}
