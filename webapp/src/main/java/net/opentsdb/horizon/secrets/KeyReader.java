/*
 * This file is part of OpenTSDB.
 *  Copyright (C) 2021 Yahoo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express  implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.opentsdb.horizon.secrets;

public abstract class KeyReader {

  private static final String PREFIX = "KEY:";

  public byte[] getKeyBytes(String key) throws KeyException {
    return (key.startsWith(PREFIX)) ? readSecret(extractKey(key)) : key.getBytes();
  }

  public String getKey(String key) throws KeyException {
    return (key.startsWith(PREFIX)) ? new String(readSecret(extractKey(key))) : key;
  }

  protected abstract byte[] readSecret(String key) throws KeyException;

  private String extractKey(String key) {
    return key.substring(PREFIX.length());
  }
}