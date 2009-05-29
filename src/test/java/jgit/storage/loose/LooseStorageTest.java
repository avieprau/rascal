package jgit.storage.loose;

import jgit.storage.AbstractStorageTest;
import jgit.storage.AbstractStorage;

public class LooseStorageTest extends AbstractStorageTest {
    @Override
    protected AbstractStorage getStorage() {
        return new LooseStorage();
    }
}
