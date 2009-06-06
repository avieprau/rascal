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

import org.junit.Before;
import rascal.storage.AbstractTempDirectoryIntegrationTest;

import java.io.File;
import java.io.IOException;

public abstract class AbstractLooseStorageLayoutDependentIntegrationTest extends AbstractTempDirectoryIntegrationTest {
    private static final String OBJECTS_DIR_NAME = "objects";

    protected LooseStorageLayout storageLayoutMock;

    protected File objectsDir;

    @Before
    public void setUpStorageLayoutMock() {
        storageLayoutMock = new DefaultLooseStorageLayout() {
            @Override
            public File getRepositoryDir() {
                return tempDir;
            }
        };
    }

    @Before
    public void setUpObjectsDir() throws IOException {
        objectsDir = new File(tempDir, OBJECTS_DIR_NAME);
        if (!objectsDir.mkdir()) {
            throw new IOException("Can't create directory for objects");
        }
    }
}
