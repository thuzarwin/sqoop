/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sqoop.job.io;

import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Test;

public class TestData extends TestCase {

  private static final double TEST_NUMBER = Math.PI + 100;
  @Test
  public void testArrayToCsv() throws Exception {
    Data data = new Data();
    String expected;
    String actual;

    // with special characters:
    expected =
        (long) TEST_NUMBER + "," +
        TEST_NUMBER + "," +
        "'" + String.valueOf(TEST_NUMBER) + "\\',s'" + "," +
        Arrays.toString(new byte[] {1, 2, 3, 4, 5});
    data.setContent(new Object[] {
        (long) TEST_NUMBER,
        TEST_NUMBER,
        String.valueOf(TEST_NUMBER) + "',s",
        new byte[] {1, 2, 3, 4, 5} },
        Data.ARRAY_RECORD);
    actual = (String)data.getContent(Data.CSV_RECORD);
    assertEquals(expected, actual);

    // with null characters:
    expected =
        (long) TEST_NUMBER + "," +
        TEST_NUMBER + "," +
        "null" + "," +
        Arrays.toString(new byte[] {1, 2, 3, 4, 5});
    data.setContent(new Object[] {
        (long) TEST_NUMBER,
        TEST_NUMBER,
        null,
        new byte[] {1, 2, 3, 4, 5} },
        Data.ARRAY_RECORD);
    actual = (String)data.getContent(Data.CSV_RECORD);
    assertEquals(expected, actual);
  }

  public static void assertEquals(Object expected, Object actual) {
    if (expected instanceof byte[]) {
      assertEquals(Arrays.toString((byte[])expected),
          Arrays.toString((byte[])actual));
    } else {
      TestCase.assertEquals(expected, actual);
    }
  }

}