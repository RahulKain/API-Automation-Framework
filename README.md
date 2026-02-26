# API Test Automation Framework

## Overview
This is a robust API Test Automation Framework built using Java, RestAssured, Cucumber, and the Page Object Model (POM) design pattern. It provides a structured approach to test REST APIs with BDD (Behavior-Driven Development) practices.

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 11+ | Programming Language |
| RestAssured | 5.4.0 | REST API Testing |
| Cucumber | 7.14.0 | BDD Framework |
| TestNG | 7.8.1 | Test Framework & Runner |
| Maven | 3.8+ | Build Tool |
| Log4j | 2.20.0 | Logging |
| Jackson | 2.15.2 | JSON Parsing |
| Hamcrest | 2.2 | Assertions |
| testng-cucumber | 1.0 | TestNG & Cucumber Integration |

## Project Structure

```
DemoAPI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── demoapi/
│   │   │           ├── api/
│   │   │           │   ├── endpoints/           # API Endpoints
│   │   │           │   │   ├── BaseEndpoint.java
│   │   │           │   │   ├── UserEndpoint.java
│   │   │           │   │   └── PostEndpoint.java
│   │   │           │   ├── models/              # Request/Response Models
│   │   │           │   │   ├── User.java
│   │   │           │   │   ├── Post.java
│   │   │           │   │   └── ErrorResponse.java
│   │   │           │   └── utils/               # Utility Classes
│   │   │           │       ├── APIConfig.java
│   │   │           │       ├── RequestBuilder.java
│   │   │           │       └── ResponseValidator.java
│   │   │           ├── pages/                   # Page Object Models
│   │   │           │   ├── BasePage.java
│   │   │           │   ├── UserAPIPage.java
│   │   │           │   └── PostAPIPage.java
│   │   │           └── listeners/
│   │   │               └── TestListener.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── log4j2.xml
│   │       └── test-config.properties
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── demoapi/
│       │           ├── stepDefinitions/         # Step Definitions
│       │           │   ├── UserSteps.java
│       │           │   └── PostSteps.java
│       │           └── hooks/
│       │               └── Hooks.java
│       └── resources/
│           └── features/                        # Feature Files
│               ├── user.feature
│               ├── post.feature
│               └── authentication.feature
├── pom.xml
├── README.md
└── .gitignore

```

## Page Object Model (POM) Architecture

The framework follows the Page Object Model design pattern where:

- **BasePage**: Abstract base class containing common methods for all API pages
- **APIPages** (e.g., UserAPIPage, PostAPIPage): Represent different API sections/resources
- **Endpoints**: Handle HTTP requests for specific API endpoints
- **Step Definitions**: Connect Gherkin scenarios to the page objects

### POM Benefits
- ✅ Maintainability: Changes in API responses only affect the page object
- ✅ Reusability: Common methods are defined once
- ✅ Scalability: Easy to add new API endpoints
- ✅ Readability: Clear separation of concerns

## Feature File Example

```gherkin
Feature: User Management API

  Scenario: Retrieve all users
    Given the user is on the User API page
    When the user retrieves all users
    Then the response status code should be 200
    And the response should contain a list of users

  Scenario: Create a new user
    Given the user is on the User API page
    When the user creates a new user with details:
      | name  | email           | age |
      | John  | john@test.com   | 30  |
    Then the response status code should be 201
    And the response should contain the user id

  Scenario: Update user details
    Given the user is on the User API page
    When the user updates user with id 1 with:
      | name | Jane |
    Then the response status code should be 200
    And the response should contain updated name
```

## Step Definitions Example

```java
public class UserSteps {
    
    private UserAPIPage userPage;
    
    @Given("the user is on the User API page")
    public void navigateToUserAPI() {
        userPage = new UserAPIPage();
    }
    
    @When("the user retrieves all users")
    public void getAllUsers() {
        userPage.getAllUsers();
    }
    
    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        userPage.verifyStatusCode(statusCode);
    }
}
```

## API Configuration

Configure your API endpoints in `application.properties`:

```properties
# Base URL Configuration
api.base.url=https://jsonplaceholder.typicode.com
api.base.path=/api/v1

# Timeouts (in seconds)
api.request.timeout=30
api.connection.timeout=10

# Logging
api.request.logging=true
api.response.logging=true
```

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific feature file
```bash
mvn clean test -Dcucumber.features=src/test/resources/features/user.feature
```

### Run tests with specific tag
```bash
mvn clean test -Dcucumber.options="--tags @smoke"
```

### Generate HTML Report
```bash
mvn clean test -Dorg.slf4j.simpleLogger.defaultLogLevel=info
```

## Test Execution Hooks

### Before Hook
- Initialize RestAssured configurations
- Setup test data
- Load environment-specific properties

### After Hook
- Log test results
- Generate reports
- Cleanup resources

## Best Practices

1. **Abstraction**: Hide API details in page objects
2. **DRY Principle**: Reuse common methods in BasePage
3. **Clear Naming**: Use descriptive names for methods and variables
4. **Assertions**: Use Hamcrest matchers for clear assertions
5. **Logging**: Log request/response for debugging
6. **Error Handling**: Handle API errors gracefully
7. **Data Management**: Use test data builders or factories
8. **Parallel Execution**: Configure for parallel test runs

## Response Validation Techniques

### JSON Path Expression
```java
String email = response.jsonPath().getString("data.user.email");
```

### Hamcrest Matchers
```java
assertThat(responseJson, hasKey("id"));
assertThat(users, hasSize(greaterThan(0)));
```

### Custom Assertions
```java
response.then()
    .statusCode(200)
    .body("user.name", equalTo("John"))
    .body("user.age", greaterThan(0));
```

## Exception Handling

The framework handles common API testing scenarios:
- Connection timeouts
- Invalid JSON responses
- Authentication failures
- Network errors
- HTTP error codes

## Logging

All requests and responses are logged using Log4j2:
- Request method, URL, and headers
- Response status and body
- Execution time
- Test outcomes

## Environment Management

Support for multiple environments:
- Development (dev)
- Staging (staging)
- Production (prod)

Pass environment as system property:
```bash
mvn clean test -Denv=staging
```

## Continuous Integration

The framework is CI/CD ready:
- ✅ Maven compatible
- ✅ TestNG integration
- ✅ Test reports generation
- ✅ Parallel execution support

## Contributing

1. Follow existing code structure
2. Add feature files for new test scenarios
3. Implement step definitions and page objects
4. Maintain consistency in naming conventions
5. Write descriptive commit messages

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Tests fail with 404 | Verify base URL in properties |
| Timeout errors | Increase timeout in configuration |
| JSON parsing errors | Check response format |
| Feature not found | Verify feature file path |

## Future Enhancements

- [ ] Database integration
- [ ] Performance testing
- [ ] Mock server integration
- [ ] GraphQL API support
- [ ] Advanced reporting dashboards
- [ ] CI/CD pipeline configuration
- [ ] Docker containerization

## Support

For issues or questions, refer to:
- RestAssured Documentation: https://rest-assured.io/
- Cucumber Documentation: https://cucumber.io/
- Log4j2 Guide: https://logging.apache.org/log4j/2.x/

---

**Version**: 1.0.0
**Last Updated**: February 26, 2026
