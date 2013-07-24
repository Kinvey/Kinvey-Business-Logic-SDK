/*
 * Copyright (c) 2013 Kinvey Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.kinvey.business_logic.collection_access;

public enum ArgumentType {
    QUERY("query"),
    OPTIONS("options"),
    SORT_ORDER("sort"),
    DOCUMENT("doc"),
    DOCUMENTS("docs"),
    KEY("key"),
    LONGITUDE("x"),
    LATITUDE("y"),
    X("x"),
    Y("y");

    private final String argumentType;

    public String getArgumentType() {
        return argumentType;
    }

    private ArgumentType(String argumentType) {
        this.argumentType = argumentType;
    }
}
