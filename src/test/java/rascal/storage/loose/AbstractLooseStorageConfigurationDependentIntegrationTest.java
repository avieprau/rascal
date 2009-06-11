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

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rascal.Configurable;
import rascal.ConfigurableTestExecutionListener;
import rascal.ConfigurationInputStreamAware;
import rascal.storage.FileSystemConfigurationSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(ConfigurableTestExecutionListener.class)
@Configurable
public abstract class AbstractLooseStorageConfigurationDependentIntegrationTest
        extends AbstractLooseStorageLayoutDependentIntegrationTest implements ConfigurationInputStreamAware {
    private static final String CONFIGURATION_FILE_NAME = "config";

    private InputStream configurationInputStream;

    protected LooseStorageConfiguration storageConfiguration;

    public void setConfigurationInputStream(InputStream stream) {
        configurationInputStream = stream;
    }

    @Before
    public void setUpStorageConfiguration() throws Exception {
        FileOutputStream outputStream
                = new FileOutputStream(new File(storageLayoutMock.getRepositoryDir(), CONFIGURATION_FILE_NAME));
        IOUtils.copy(configurationInputStream, outputStream);
        outputStream.close();
        configurationInputStream.close();
        storageConfiguration
                = new DefaultLooseStorageConfiguration(new FileSystemConfigurationSource(storageLayoutMock));
    }
}
