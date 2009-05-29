package jgit.storage.loose;

import jgit.storage.AbstractStorage;
import jgit.storage.AbstractStorageTest;

public class LooseStorageTest extends AbstractStorageTest {
    @Override
    protected AbstractStorage getStorage() {
        return new LooseStorage();
    }
}
