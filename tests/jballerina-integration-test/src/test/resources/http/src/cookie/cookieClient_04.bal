// Copyright (c) 2020 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

import ballerina/http;
import ballerina/io;

public function main() {
    http:CsvPersistentCookieHandler myPersistentStore = new("./cookies-test-data/client-4.csv");
    http:Client cookieClientEndpoint = new ("http://localhost:9253", {
            cookieConfig: { enabled: true, persistentCookieHandler: myPersistentStore }
        });
    http:Request req = new;
    // Server sends the session cookies in the response for the first request.
    var response = cookieClientEndpoint->get("/cookie/cookieBackend_3", req);
    // Server removes an existing session cookie in the cookie store by sending an expired cookie in the response.
    response = cookieClientEndpoint->get("/cookie/cookieBackend_3", req);
    // Third request after removing the cookie.
    response = cookieClientEndpoint->get("/cookie/cookieBackend_3", req);
    if (response is http:Response) {
        var payload = response.getTextPayload();
        if (payload is string) {
            io:print(payload);
        }
    }
}
