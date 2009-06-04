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

package rascal.object;

public class CorruptedObjectException extends Exception {
    public CorruptedObjectException(String objectName, String message) {
        super(String.format("Object with name '%s' is corrupted: %s", objectName, message));
    }

    public CorruptedObjectException(String objectName, Throwable cause) {
        super(String.format("Object with name '%s' is corrupted", objectName), cause);
    }
}
