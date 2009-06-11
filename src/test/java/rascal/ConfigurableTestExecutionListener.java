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

package rascal;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.io.InputStream;

public class ConfigurableTestExecutionListener extends AbstractTestExecutionListener {
    private static final String CONFIGURATION_NAME_SUFFIX = ".config";

    private static final String CONFIGURATION_NAME_SEPARATOR = "-";

    private void injectConfiguration(TestContext testContext, String configurationPath) {
        configurationPath = configurationPath.replace('.', '/') + CONFIGURATION_NAME_SUFFIX;
        if (testContext.getTestInstance() instanceof ConfigurationInputStreamAware) {
            ConfigurationInputStreamAware aware = (ConfigurationInputStreamAware) testContext.getTestInstance();
            InputStream stream = aware.getClass().getClassLoader().getResourceAsStream(configurationPath);
            if (stream == null) {
                throw new RuntimeException("Configuration resource not found");
            }
            aware.setConfigurationInputStream(stream);
        }
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        Configurable configurable = testContext.getTestMethod().getAnnotation(Configurable.class);
        if (configurable != null) {
            if (configurable.value().isEmpty()) {
                String defaultPath = testContext.getTestClass().getName() + CONFIGURATION_NAME_SEPARATOR
                        + testContext.getTestMethod().getName();
                injectConfiguration(testContext, defaultPath);
            } else {
                injectConfiguration(testContext, configurable.value());
            }
        } else {
            Class<?> testClass = testContext.getTestClass();
            while ((configurable = testClass.getAnnotation(Configurable.class)) == null) {
                testClass = testClass.getSuperclass();
                if (testClass == null || testClass.equals(Object.class)) {
                    break;
                }
            }
            if (configurable != null) {
                if (configurable.value().isEmpty()) {
                    String defaultPath = testClass.getName();
                    injectConfiguration(testContext, defaultPath);
                } else {
                    injectConfiguration(testContext, configurable.value());
                }
            }
        }
    }
}
