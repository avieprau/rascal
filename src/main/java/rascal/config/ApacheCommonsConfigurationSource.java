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

public abstract class ApacheCommonsConfigurationSource implements ConfigurationSource {
    protected abstract org.apache.commons.configuration.Configuration getCommonsConfiguration();

    private void checkPropertyExists(String key) throws ConfigurationPropertyNotFoundException {
        if (!getCommonsConfiguration().containsKey(key)) {
            throw new ConfigurationPropertyNotFoundException(key);
        }
    }

    public String getString(String key) throws ConfigurationPropertyNotFoundException {
        checkPropertyExists(key);
        return getCommonsConfiguration().getString(key);
    }

    public int getInteger(String key) throws ConfigurationPropertyNotFoundException {
        checkPropertyExists(key);
        return getCommonsConfiguration().getInt(key);
    }

    public boolean getBoolean(String key) throws ConfigurationPropertyNotFoundException {
        checkPropertyExists(key);
        return getCommonsConfiguration().getBoolean(key);
    }
}
