package jgit.repository;

import org.springframework.beans.factory.FactoryBean;

import java.security.MessageDigest;

public class SHA1MessageDigestFactoryBean implements FactoryBean {
    private static final String SHA1_ALGORITHM_NAME = "SHA-1";

    public Object getObject() throws Exception {
        return MessageDigest.getInstance(SHA1_ALGORITHM_NAME);
    }

    public Class getObjectType() {
        return MessageDigest.class;
    }

    public boolean isSingleton() {
        return false;
    }
}
