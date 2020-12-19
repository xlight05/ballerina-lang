#bin/bash

./../gradlew clean build -x test -x check
/bin/cp -rf modules/langserver-core/build/libs/language-server-core-2.0.0-Preview8-SNAPSHOT.jar /home/anjana/ballerina-swan-lake-preview8-SNAPSHOT1/distributions/ballerina-slp8/lib/tools/lang-server/lib/
/bin/cp -rf modules/langserver-compiler/build/libs/language-server-compiler-2.0.0-Preview8-SNAPSHOT.jar /home/anjana/ballerina-swan-lake-preview8-SNAPSHOT1/distributions/ballerina-slp8/lib/tools/lang-server/lib/
/bin/cp -rf modules/langserver-commons/build/libs/language-server-commons-2.0.0-Preview8-SNAPSHOT.jar /home/anjana/ballerina-swan-lake-preview8-SNAPSHOT1/distributions/ballerina-slp8/lib/tools/lang-server/lib/

/bin/cp -rf modules/langserver-core/build/libs/language-server-core-2.0.0-Preview8-SNAPSHOT.jar /home/anjana/ballerina-swan-lake-preview8-SNAPSHOT1/distributions/ballerina-slp8/bre/lib/
/bin/cp -rf modules/langserver-compiler/build/libs/language-server-compiler-2.0.0-Preview8-SNAPSHOT.jar /home/anjana/ballerina-swan-lake-preview8-SNAPSHOT1/distributions/ballerina-slp8/bre/lib/
/bin/cp -rf modules/langserver-commons/build/libs/language-server-commons-2.0.0-Preview8-SNAPSHOT.jar /home/anjana/ballerina-swan-lake-preview8-SNAPSHOT1/distributions/ballerina-slp8/bre/lib/

echo "Copied";