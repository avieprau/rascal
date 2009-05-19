package jgit;

import org.junit.runner.RunWith;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Mockery;

@RunWith(JMock.class)
public class AbstractTestCase {
    protected Mockery context = new JUnit4Mockery();
}
