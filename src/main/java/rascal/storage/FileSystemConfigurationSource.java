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

package rascal.storage;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import rascal.config.ApacheCommonsConfigurationSource;
import rascal.config.ConfigurationSource;

public class FileSystemConfigurationSource extends ApacheCommonsConfigurationSource implements ConfigurationSource {
    private Configuration commonsConfiguration;

    public FileSystemConfigurationSource(FileSystemStorageLayout storageLayout) {
        try {
            commonsConfiguration = new HierarchicalINIConfiguration(storageLayout.getConfigurationFile());
        } catch (ConfigurationException e) {
            // TODO: some special excaptions for this case
            throw new RuntimeException(e);
        }
    }

    protected Configuration getCommonsConfiguration() {
        return commonsConfiguration;
    }
}
