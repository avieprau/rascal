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

package jgit.storage.loose;

import jgit.storage.DefaultFileSystemStorageLayout;

import java.io.File;

public class DefaultLooseStorageLayout extends DefaultFileSystemStorageLayout implements LooseStorageLayout {
    private static final String OBJECTS_DIR_NAME = "objects";

    private static final int OBJECT_DIR_NAME_LENGTH = 2;

    public File getObjectsDir() {
        return new File(getRepositoryDir(), OBJECTS_DIR_NAME);
    }

    public File getObjectDirForName(String objectName) {
        return new File(getObjectsDir(), objectName.substring(0, OBJECT_DIR_NAME_LENGTH));
    }

    public File getObjectFileForName(String objectName) {
        return new File(getObjectDirForName(objectName), objectName.substring(OBJECT_DIR_NAME_LENGTH));
    }
}
