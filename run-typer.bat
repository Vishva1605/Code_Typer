@echo off
setlocal

:: Set file paths
set FILE=CodeTyper
set LIB=jnativehook-2.2.2.jar

:: Compile
echo 📦 Compiling %FILE%.java...
javac -cp ".;%LIB%" %FILE%.java

if %ERRORLEVEL% NEQ 0 (
    echo ❌ Compilation failed.
    pause
    exit /b
)

:: Run
echo 🚀 Running %FILE%...
java -cp ".;%LIB%" %FILE%

pause