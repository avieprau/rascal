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

import org.junit.After;
import org.junit.Before;

import java.io.File;

public abstract class AbstractTempFileIntegrationTest extends AbstractIntegrationTest {
    private static final String TEMP_FILE_PREFIX = "rascal_test";

    private static final String TEMP_FILE_SUFFIX = ".tmp";

    protected File tempFile;

    @Before
    public void setUpTempFile() throws Exception {
        tempFile = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    @After
    public void tearDownTempFile() throws Exception {
        tempFile.delete();
    }
}
