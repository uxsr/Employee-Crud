@echo off
echo Building the application...
call mvnw.cmd clean package -DskipTests
if %ERRORLEVEL% neq 0 (
    echo Build failed with error code %ERRORLEVEL%
    pause
    exit /b %ERRORLEVEL%
)

echo Starting the application...
java -jar target/employee-management-system-0.0.1-SNAPSHOT.jar

if %ERRORLEVEL% neq 0 (
    echo.
    echo Application failed to start with error code %ERRORLEVEL%
    pause
) else (
    echo.
    echo Application started successfully!
    echo Access the application at http://localhost:8080/api/employees
    echo Access H2 console at http://localhost:8080/h2-console
    echo.
    pause
)
