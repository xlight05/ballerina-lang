// Copyright (c) 2021 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import configStructuredTypes.type_defs;
import testOrg/configLib.mod1 as configLib;
import ballerina/test;

configurable table<Engineer> & readonly engineerTable = ?;
configurable table<Lecturer> & readonly lecturerTable = ?;
configurable table<Lawyer> & readonly lawyerTable = ?;

// From non-default module
configurable table<type_defs:Student> & readonly studentTable = ?;
configurable table<type_defs:Officer> & readonly officerTable = ?;
configurable table<type_defs:Employee> & readonly employeeTable = ?;
configurable table<type_defs:Officer & readonly> & readonly officerTable1 = ?;
configurable table<type_defs:Employee & readonly> & readonly employeeTable1 = ?;
configurable table<type_defs:Lecturer> & readonly lecturerTable2 = ?;
configurable table<type_defs:Lawyer> & readonly lawyerTable2 = ?;

// Complex records
configurable table<type_defs:Person> & readonly personTable = ?;

// From non-default package
configurable table<configLib:Manager> & readonly managerTable = ?;
configurable table<configLib:Teacher> & readonly teacherTable = ?;
configurable table<configLib:Farmer> & readonly farmerTable = ?;

public function testTables() {
    test:assertEquals(engineerTable.toString(), "[{\"name\":\"hinduja\",\"id\":100},{\"name\":\"riyafa\",\"id\":105}]");
    test:assertEquals(studentTable.toString(), "[{\"name\":\"manu\",\"id\":100},{\"name\":\"riyafa\",\"id\":105}]");
    test:assertEquals(employeeTable.toString(), "[{\"name\":\"hinduja\",\"id\":102},{\"name\":\"manu\",\"id\":100}]");
    test:assertEquals(employeeTable1.toString(), "[{\"name\":\"waruna\",\"id\":2},{\"name\":\"manu\",\"id\":7}]");
    test:assertEquals(officerTable.toString(), "[{\"name\":\"hinduja\",\"id\":102},{\"name\":\"manu\",\"id\":100}]");
    test:assertEquals(officerTable1.toString(), "[{\"name\":\"waruna\",\"id\":4},{\"name\":\"gabilan\",\"id\":5}]");
    test:assertEquals(managerTable.toString(), "[{\"name\":\"gabilan\",\"id\":101},{\"name\":\"riyafa\",\"id\":102}]");
    test:assertEquals(teacherTable.toString(), "[{\"name\":\"manu\",\"id\":77},{\"name\":\"riyafa\",\"id\":88}]");
    test:assertEquals(farmerTable.toString(), "[{\"name\":\"waruna\",\"id\":444},{\"name\":\"hinduja\",\"id\":888}]");
    test:assertEquals(personTable.toString(), "[{\"name\":\"riyafa\",\"id\":13," + 
        "\"address\":{\"city\":\"Canberra\",\"country\":{\"name\":\"Australia\"}}},{\"name\":\"gabilan\"," + 
        "\"id\":14,\"address\":{\"city\":\"Paris\",\"country\":{\"name\":\"France\"}}}]");
}