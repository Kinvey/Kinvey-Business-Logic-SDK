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
package com.kinvey.business_logic.interceptors;

import com.kinvey.business_logic.KinveyAuthCredentials;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;


public class KinveyAuthInterceptor implements ContainerRequestFilter {

    public class AuthCredentials {
        private String username;
        private String password;

        void parseHeader(String header){
//            try {
//
                String bytes = Base64.base64Decode(header.replaceFirst("[Bb]asic ", ""));
                String[] tmp = bytes.split(":");
                username = tmp[0];
                password = tmp[1];
//            } catch (UnsupportedEncodingException ee){
//                username = "";
//                password = "";
//            }
        }

        public AuthCredentials(String authHeader){
            username = null;
            password = null;
            parseHeader(authHeader);
        }

        public String getUsername(){
            return username;
        }
        public String getPassword(){
            return password;
        }
    }

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        String path = request.getPath();

        if (path.equals("warmup") || path.equals("ping") || path.equals("heartbeat")) {
            return request;
        }

        // Extract header
        String authHeader = request.getHeaderValue("authorization");

        if (authHeader == null){
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        KinveyAuthCredentials expectedAuth = KinveyAuthCredentials.getInstance();

        AuthCredentials ac = new AuthCredentials(authHeader);

        if (!ac.getUsername().equals(expectedAuth.getAppId()) || !ac.getPassword().equals(expectedAuth.getMasterSecret())){
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        return request;
    }
}
