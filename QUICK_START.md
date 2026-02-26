# Quick Start Guide

## Prerequisites

1. **Java 11 or higher** installed
   ```bash
   java -version
   ```

2. **Maven 3.8 or higher** installed
   ```bash
   mvn -version
   ```

3. **Git** (optional, for version control)
   ```bash
   git --version
   ```

## Project Setup

### Step 1: Navigate to Project Directory
```bash
cd c:\Users\Asus\Downloads\DemoAPI
```

### Step 2: Clean and Build Project
```bash
mvn clean
```

### Step 3: Build the Project
```bash
mvn install
```

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Tests with Specific Tag
```bash
# Smoke tests only
mvn clean test -Dcucumber.options="--tags @smoke"

# Regression tests only
mvn clean test -Dcucumber.options="--tags @regression"

# User API tests only
mvn clean test -Dcucumber.options="--tags @user"

# API validation tests only
mvn clean test -Dcucumber.options="--tags @api-validation"
```

### Run Specific Feature File
```bash
mvn clean test -Dcucumber.features=src/test/resources/features/user.feature
```

## Test Results & Reports

After running tests, reports are generated in:

- **HTML Report**: `target/cucumber-reports/index.html`
- **JSON Report**: `target/cucumber-reports/cucumber.json`
- **JUnit XML**: `target/cucumber-reports/cucumber.xml`
- **Console Output**: View in terminal

To view HTML report:
```bash
# Windows
start target/cucumber-reports/index.html

# Mac
open target/cucumber-reports/index.html

# Linux
xdg-open target/cucumber-reports/index.html
```

## Project Files Overview

| File | Purpose |
|------|---------|
| `README.md` | Framework documentation |
| `testcases.md` | Detailed test case documentation |
| `MODULE1_IMPLEMENTATION.md` | Module 1 implementation summary |
| `QUICK_START.md` | This file |
| `pom.xml` | Maven configuration |
| `.gitignore` | Git ignore patterns |

## Configuration

Edit `src/main/resources/application.properties` to customize:

```properties
# API Configuration
api.base.url=http://localhost:8080      # Base URL
api.base.path=/api/v1                   # API path
api.request.timeout=30000               # Request timeout in ms
api.connection.timeout=10000            # Connection timeout in ms

# Logging
api.request.logging=true                # Log requests
api.response.logging=true               # Log responses

# Environment
environment=local                       # Environment name
max.retries=3                          # Max retries
```

## Project Structure

```
DemoAPI/
├── src/main/java/com/demoapi/
│   ├── api/
│   │   ├── endpoints/     # API endpoints
│   │   ├── models/        # Data models
│   │   └── utils/         # Utility classes
│   └── pages/             # Page objects
├── src/main/resources/
│   ├── application.properties
│   └── log4j2.xml
├── src/test/java/com/demoapi/
│   ├── stepDefinitions/   # Cucumber steps
│   ├── hooks/             # Test hooks
│   └── runners/           # Test runners
├── src/test/resources/
│   ├── features/          # Feature files
│   ├── testdata/          # Test data
│   └── testng.xml         # TestNG config
└── pom.xml                # Maven config
```

## Troubleshooting

### Issue: Tests fail with 404
**Solution**: Verify the base URL in `application.properties` matches your local API server

### Issue: Timeout errors
**Solution**: Increase timeout values in `application.properties`:
```properties
api.request.timeout=60000
api.connection.timeout=20000
```

### Issue: Import errors in IDE
**Solution**: Run Maven update in IDE or execute:
```bash
mvn eclipse:eclipse
mvn idea:idea
```

### Issue: Feature file not found
**Solution**: Ensure feature files are in `src/test/resources/features/` directory

### Issue: Step definitions not recognized
**Solution**: Check package names in glue path in `CucumberTestRunner.java`:
```java
glue = {"com.demoapi.hooks", "com.demoapi.stepDefinitions"}
```

## Logging

Log files are generated at:
- `target/logs/api-framework.log` - All logs
- `target/logs/api-framework-errors.log` - Error logs only

View logs:
```bash
# Windows
type target\logs\api-framework.log

# Mac/Linux
cat target/logs/api-framework.log

# Follow real-time logs
tail -f target/logs/api-framework.log
```

## IDE Setup

### IntelliJ IDEA
1. File → Open → Select `pom.xml`
2. Right-click on `pom.xml` → Maven → Reload project
3. Run → Edit Configurations → Add TestNG configuration

### Eclipse
1. File → Import → Maven → Existing Maven Projects
2. Select project directory
3. Right-click on project → Maven → Update Project

### VS Code
1. Install extensions:
   - Extension Pack for Java
   - Cucumber (Gherkin) Full Support
   - REST Client
2. Open project folder
3. Terminal → New Terminal → Run `mvn clean test`

## Parallel Test Execution

To run tests in parallel, modify `testng.xml`:

```xml
<suite name="API Test Suite" parallel="methods" thread-count="4">
</suite>
```

Or via Maven:
```bash
mvn clean test -DthreadCount=4
```

## CI/CD Integration

### GitHub Actions Example
```yaml
name: API Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: 11
      - name: Run tests
        run: mvn clean test
```

## Common Commands

```bash
# Clean project
mvn clean

# Compile code
mvn compile

# Download dependencies
mvn dependency:resolve

# Run tests
mvn test

# Run specific test class
mvn test -Dtest=UserSteps

# Generate project reports
mvn site

# Package project
mvn package

# Install to local repository
mvn install

# Deploy to remote repository
mvn deploy
```

## Next Steps

1. ✅ Module 1: User Management API (COMPLETE)
2. 📋 Module 2: Post Management API
3. 📋 Module 3: Authentication API
4. 📋 Module 4: Comments API
5. 📋 Module 5: Notification API

## Support & Documentation

- **RestAssured**: https://rest-assured.io/
- **Cucumber**: https://cucumber.io/docs/gherkin/
- **TestNG**: https://testng.org/doc/index.html
- **Log4j2**: https://logging.apache.org/log4j/2.x/
- **Maven**: https://maven.apache.org/

## Contact & Issues

For issues or questions, refer to:
1. Check existing test cases in `testcases.md`
2. Review feature files in `src/test/resources/features/`
3. Check logs in `target/logs/`
4. Review error messages in test reports

---

**Last Updated**: February 26, 2026  
**Framework Version**: 1.0.0
