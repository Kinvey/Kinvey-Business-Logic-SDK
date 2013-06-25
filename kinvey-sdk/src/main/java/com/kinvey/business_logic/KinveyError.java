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
package com.kinvey.business_logic;

import java.util.HashMap;

/**
 * @author mjsalinger
 * @since 2.0
 */
public class KinveyError {

    private static final String KINVEY_USER_ERROR = "UserError";
    private HashMap<String, Object> errorBody = new HashMap<String, Object>();


    public KinveyError(String description, String debug, Exception ex) {
        errorBody = new HashMap<String, Object>();
        errorBody.put("isError", true);
        errorBody.put("description", description);
        String debugMsg = new StringBuilder()
                .append(ex != null ? ex.getClass().getCanonicalName() : KINVEY_USER_ERROR)
                .append(debug)
                .toString();
        errorBody.put("debugMessage", debugMsg);
    }

    public HashMap<String, Object> getErrorBody() {
        return errorBody;
    }

    public static class Builder {
        private static final String BL_RUNTIME_ERROR = "BLRuntimeError";
        private String debug;
        private Exception exception;

        public Builder() {

        }

        public KinveyError build() {
            if (exception != null && debug == null) {
                debug = exception.getMessage();
            }
            return new KinveyError(BL_RUNTIME_ERROR, debug, exception);
        }

        public String getDebug() {
            return debug;
        }

        public void setDebug(String debug) {
            this.debug = debug;
        }

        public Exception getException() {
            return exception;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }
    }

}
