package rascal;

import junit.framework.AssertionFailedError;
import org.springframework.test.AssertThrows;

public abstract class AssertThrowsWithCause extends AssertThrows {
    private Class<? extends Exception> expectedCauseException;

    protected AssertThrowsWithCause(Class<? extends Exception> expectedException,
                                    Class<? extends Exception> expectedCauseException) {
        super(expectedException);
        this.expectedCauseException = expectedCauseException;
    }

    protected AssertThrowsWithCause(Class<? extends Exception> expectedException,
                                    Class<? extends Exception> expectedCauseException, String failureMessage) {
        super(expectedException, failureMessage);
        this.expectedCauseException = expectedCauseException;
    }

    protected Class<? extends Exception> getExpectedCauseException() {
        return expectedCauseException;
    }

    @Override
    protected void checkExceptionExpectations(Exception actualException) {
        super.checkExceptionExpectations(actualException);
        if (actualException.getCause() == null
                || !getExpectedCauseException().isAssignableFrom(actualException.getCause().getClass())) {
            throw new AssertionFailedError(String.format("Should have thrown exception with cause [%s]",
                    getExpectedCauseException().getName()));
        }
    }
}
