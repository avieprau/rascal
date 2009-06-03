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

package jgit.storage;

import jgit.AbstractIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

public class AbstractTempDirectoryIntegrationTest extends AbstractIntegrationTest {
    private static final String TEMP_DIR_NAME_PREFIX = "jgit_test_";

    private static final int TEMP_DIR_SUFFIX_LENGTH = 6;

    protected File tempDir;

    @Before
    public void setUpTempDirectory() throws IOException {
        File tempDirRoot = new File(System.getProperty("java.io.tmpdir"));
        tempDir = new File(tempDirRoot, TEMP_DIR_NAME_PREFIX
                + RandomStringUtils.randomNumeric(TEMP_DIR_SUFFIX_LENGTH));
        if (!tempDir.mkdir()) {
            throw new IOException("Can't create temprorary directory for testing");
        }
    }

    @After
    public void tearDownTempDirectory() throws IOException {
        FileUtils.deleteDirectory(tempDir);
    }
}
