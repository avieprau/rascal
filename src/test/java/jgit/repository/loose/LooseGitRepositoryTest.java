package jgit.repository.loose;

import jgit.repository.AbstractGitRepositoryTest;
import jgit.repository.AbstractGitRepository;

public class LooseGitRepositoryTest extends AbstractGitRepositoryTest {
    protected AbstractGitRepository getGitRepository() {
        return new LooseGitRepository();
    }
}
