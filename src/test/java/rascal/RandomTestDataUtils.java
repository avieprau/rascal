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

import org.apache.commons.lang.math.RandomUtils;

public class RandomTestDataUtils {
    private static final int DEFAULT_TEST_DATA_SIZE = 1024;

    public static byte[] createRandomData(int size) {
        byte[] data = new byte[size];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (RandomUtils.nextInt(Byte.MAX_VALUE * 2) + Byte.MIN_VALUE);
        }
        return data;
    }

    public static byte[] createRandomData() {
        return createRandomData(DEFAULT_TEST_DATA_SIZE);
    }
}
