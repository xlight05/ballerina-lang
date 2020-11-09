#bin/bash

./../gradlew clean build -x test -x checkstyleMain
/bin/cp -rf modules/langserver-core/build/libs/language-server-core-2.0.0-Preview7-SNAPSHOT.jar /home/anjana/Downloads/jballerina-tools-2.0.0-Preview7-SNAPSHOT/lib/tools/lang-server/lib/
/bin/cp -rf modules/langserver-compiler/build/libs/language-server-compiler-2.0.0-Preview7-SNAPSHOT.jar /home/anjana/Downloads/jballerina-tools-2.0.0-Preview7-SNAPSHOT/lib/tools/lang-server/lib/
/bin/cp -rf modules/langserver-commons/build/libs/language-server-commons-2.0.0-Preview7-SNAPSHOT.jar /home/anjana/Downloads/jballerina-tools-2.0.0-Preview7-SNAPSHOT/lib/tools/lang-server/lib/

/bin/cp -rf modules/langserver-core/build/libs/language-server-core-2.0.0-Preview7-SNAPSHOT.jar /home/anjana/Downloads/jballerina-tools-2.0.0-Preview7-SNAPSHOT/bre/lib/
/bin/cp -rf modules/langserver-compiler/build/libs/language-server-compiler-2.0.0-Preview7-SNAPSHOT.jar /home/anjana/Downloads/jballerina-tools-2.0.0-Preview7-SNAPSHOT/bre/lib/
/bin/cp -rf modules/langserver-commons/build/libs/language-server-commons-2.0.0-Preview7-SNAPSHOT.jar /home/anjana/Downloads/jballerina-tools-2.0.0-Preview7-SNAPSHOT/bre/lib/

echo "Copied";
