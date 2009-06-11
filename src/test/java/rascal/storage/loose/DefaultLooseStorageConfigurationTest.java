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

package rascal.storage.loose;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Test;
import rascal.config.ConfigurationPropertyNotFoundException;
import rascal.storage.DefaultStorageConfigurationTest;

public class DefaultLooseStorageConfigurationTest extends DefaultStorageConfigurationTest {
    private DefaultLooseStorageConfiguration defaultLooseStorageConfiguration;

    private static final String LOOSE_COMPRESSION_LEVEL_PROPERTY_NAME = "core.loosecompression";

    private static final int DEFAULT_LOOSE_COMPRESSION_LEVEL = 1;

    @Override
    public void setUp() {
        defaultLooseStorageConfiguration = new DefaultLooseStorageConfiguration(configurationSourceMock);
        defaultStorageConfiguration = defaultLooseStorageConfiguration;
    }

    @Test
    public void testGetLooseCompressionLevel() throws Exception {
        final int value = 4;
        context.checking(new Expectations() {
            {
                oneOf(configurationSourceMock).getInteger(LOOSE_COMPRESSION_LEVEL_PROPERTY_NAME);
                will(returnValue(value));
            }
        });
        Assert.assertEquals(value, defaultLooseStorageConfiguration.getLooseCompressionLevel());
    }

    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    @Test
    public void testGetDefaultLooseCompressionLevel() throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(configurationSourceMock).getInteger(LOOSE_COMPRESSION_LEVEL_PROPERTY_NAME);
                will(throwException(new ConfigurationPropertyNotFoundException(LOOSE_COMPRESSION_LEVEL_PROPERTY_NAME)));
            }
        });
        Assert.assertEquals(DEFAULT_LOOSE_COMPRESSION_LEVEL,
                defaultLooseStorageConfiguration.getLooseCompressionLevel());
    }
}
