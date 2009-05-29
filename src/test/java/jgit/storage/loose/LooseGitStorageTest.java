package jgit.storage.loose;

import jgit.storage.AbstractGitStorageTest;
import jgit.storage.AbstractGitStorage;

public class LooseGitStorageTest extends AbstractGitStorageTest {
    @Override
    protected AbstractGitStorage getGitStorage() {
        return new LooseGitStorage();
    }
}
