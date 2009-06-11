/*
 * Copyright 2009, Andrej Viepraŭ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rascal.config;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.AssertThrows;
import rascal.AbstractTest;

public abstract class AbstractConfigurationTest extends AbstractTest {
    private static final String PROPERTY_KEY = "test";

    protected ConfigurationSource configurationSourceMock;

    protected abstract AbstractConfiguration getConfiguration();

    @Before
    public void setUpConfigurationSourceMock() {
        configurationSourceMock = context.mock(ConfigurationSource.class);
    }

    @Test
    public void testGetStringProperty() throws Exception {
        final String value = "value test";
        context.checking(new Expectations() {
            {
                oneOf(configurationSourceMock).getString(PROPERTY_KEY);
                will(returnValue(value));
            }
        });
        Assert.assertEquals(value, getConfiguration().getStringProperty(PROPERTY_KEY));
    }

    @Test
    public void testGetIntegerProperty() throws Exception {
        final int value = 100500;
        context.checking(new Expectations() {
            {
                oneOf(configurationSourceMock).getInteger(PROPERTY_KEY);
                will(returnValue(value));
            }
        });
        Assert.assertEquals(value, getConfiguration().getIntegerProperty(PROPERTY_KEY));
    }

    @Test
    public void testGetBooleanProperty() throws Exception {
        final boolean value = true;
        context.checking(new Expectations() {
            {
                oneOf(configurationSourceMock).getBoolean(PROPERTY_KEY);
                will(returnValue(value));
            }
        });
        Assert.assertEquals(value, getConfiguration().getBooleanProperty(PROPERTY_KEY));
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    @Test
    public void testConfigurationPropertyNotFound() throws Exception {
        context.checking(new Expectations() {
            {
                one(configurationSourceMock).getString(PROPERTY_KEY);
                will(throwException(new ConfigurationPropertyNotFoundException(PROPERTY_KEY)));
            }
        });
        new AssertThrows(ConfigurationPropertyNotFoundException.class) {
            public void test() throws Exception {
                getConfiguration().getStringProperty(PROPERTY_KEY);
            }
        }.runTest();
    }
}
