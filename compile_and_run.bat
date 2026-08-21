@echo off
setlocal enabledelayedexpansion
echo Compiling Student Management System...

:: Create lib directory if it doesn't exist
if not exist lib mkdir lib

:: Download Apache POI dependencies if they don't exist
if not exist lib\poi-5.2.3.jar (
    echo Downloading Apache POI dependencies...
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/poi/poi/5.2.3/poi-5.2.3.jar' -OutFile 'lib\poi-5.2.3.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/poi/poi-ooxml/5.2.3/poi-ooxml-5.2.3.jar' -OutFile 'lib\poi-ooxml-5.2.3.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/commons-io/commons-io/2.11.0/commons-io-2.11.0.jar' -OutFile 'lib\commons-io-2.11.0.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/commons-codec/commons-codec/1.15/commons-codec-1.15.jar' -OutFile 'lib\commons-codec-1.15.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/xmlbeans/xmlbeans/5.1.1/xmlbeans-5.1.1.jar' -OutFile 'lib\xmlbeans-5.1.1.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/commons/commons-compress/1.21/commons-compress-1.21.jar' -OutFile 'lib\commons-compress-1.21.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/zaxxer/SparseBitSet/1.2/SparseBitSet-1.2.jar' -OutFile 'lib\SparseBitSet-1.2.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.18.0/log4j-api-2.18.0.jar' -OutFile 'lib\log4j-api-2.18.0.jar'}"
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/poi/poi-ooxml-lite/5.2.3/poi-ooxml-lite-5.2.3.jar' -OutFile 'lib\poi-ooxml-lite-5.2.3.jar'}"
)

:: Make sure commons-collections4 is downloaded (this was missing or not properly included)
if not exist lib\commons-collections4-4.4.jar (
    echo Downloading commons-collections4 dependency...
    powershell -Command "& {Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/commons/commons-collections4/4.4/commons-collections4-4.4.jar' -OutFile 'lib\commons-collections4-4.4.jar'}"
)

:: Set classpath with all dependencies
set CLASSPATH=.
for %%i in (lib\*.jar) do set CLASSPATH=!CLASSPATH!;%%i

:: Compile all Java files
echo Compiling Java files...
javac -cp "%CLASSPATH%" *.java

:: Check if compilation was successful
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

:: Run the application
echo Starting Student Management System...
java -cp "%CLASSPATH%" StudentManagementSystem

pause