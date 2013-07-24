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


/**
 * An incoming request from Kinvey.
 *
 * @param <T>  the type specifier for the arguments of this request.
 *
 */
public class Request<T> {

    /**
     * The vanilla constructor required for JSON serialization.
     */
    public Request() {
    }

    /**
     * A convenience constructor for initializing all member variables.
     *
     * @param command  the command that's being executed
     * @param kinveyAction  the name of the Kinvey action being requested (save, fetch or delete)
     * @param httpAction  the HTTP verb that generated the request
     * @param arguments  the command specific arguments
     */
    public Request(String command, String kinveyAction, String httpAction, T arguments) {
        this.command = command;
        this.kinveyAction = kinveyAction;
        this.httpAction = httpAction;
        this.arguments = arguments;
    }

    private String command;
    private String kinveyAction;
    private String httpAction;
    private T arguments;

    public String getCommand() {
        return command;
    }

    public String getKinveyAction() {
        return kinveyAction;
    }

    public String getHttpAction() {
        return httpAction;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setKinveyAction(String kinveyAction) {
        this.kinveyAction = kinveyAction;
    }

    public void setHttpAction(String httpAction) {
        this.httpAction = httpAction;
    }

    public void setArguments(T arguments) {
        this.arguments = arguments;
    }

    public T getArguments() {
        return arguments;
    }
}
