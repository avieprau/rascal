package jgit.storage.loose;

import jgit.storage.AbstractGitStorageTest;
import jgit.storage.AbstractGitStorage;

public class LooseGitStorageTest extends AbstractGitStorageTest {
    protected AbstractGitStorage getGitRepository() {
        return new LooseGitStorage();
    }
}
