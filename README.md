# SauceDemo Test Automation

Selenium WebDriver test automation project for the saucedemo.com login functionality. Built as a learning project to practice core test automation concepts: design patterns, BDD, parallel execution, and structured logging.

## Prerequisites

- Java 23 (JDK)
- Maven 3.x
- Chrome and Firefox browsers installed

## How to Run

```bash
# Run all tests — Chrome and Firefox in parallel
mvn clean test

# Run in headless mode (no browser window)
mvn clean test -Dheadless=true

# Chrome only
mvn clean test -Dtest=ChromeRunnerTest

# Firefox only
mvn clean test -Dtest=FirefoxRunnerTest
```

Chrome and Firefox run simultaneously in separate JVM forks via Maven Surefire (`forkCount=2`). Each fork uses its own runner class and browser-specific hooks.

After a test run, open `target/cucumber-reports/report.html` in your browser for the Cucumber HTML report.

## Test Cases

All three use cases are implemented as Cucumber **Scenario Outlines** with Examples tables, giving **4 scenario executions** total per browser.

| UC | Scenario | Expected Result | Example Rows |
|----|----------|----------------|-------------|
| UC-1 | Empty credentials (both fields cleared) | Error: "Epic sadface: Username is required" | 1 |
| UC-2 | Username only (password cleared after entry) | Error: "Epic sadface: Password is required" | 1 |
| UC-3 | Valid login | Dashboard title shows "Swag Labs" | 2 (`standard_user`, `visual_user`) |

## Tech Stack

| Tool | Version / Detail |
|------|-----------------|
| Java | 23 |
| Maven | Build & dependency management |
| Selenium WebDriver | 4.25.0 |
| Cucumber | 7.18.1 + JUnit 5 (`junit-platform-suite` 1.10.3) |
| AssertJ | 3.26.3 |
| SLF4J + Logback | 1.5.6 |
| Locators | CSS selectors only |

## Design Patterns

### Abstract Factory (`core/` package)

`BrowserFactory` interface defines `createDriver()`. Two concrete implementations — `ChromeBrowserFactory` and `FirefoxBrowserFactory` — create pre-configured WebDriver instances. Each factory checks `Config.HEADLESS` to support headed/headless mode.

### Decorator (`core/` package)

`LoggingDriverDecorator` implements `WebDriverListener` and is applied to the driver via Selenium's `EventFiringDecorator`. It intercepts and logs every action (navigate, find element, click, send keys, clear, quit) through SLF4J, providing a full trace of browser interactions without modifying page object code.

## Project Structure

```
saucedemo-ta/
├── src/
│   ├── main/java/com/saucedemo/
│   │   ├── core/
│   │   │   ├── BrowserFactory.java          # Abstract Factory interface
│   │   │   ├── ChromeBrowserFactory.java     # Chrome WebDriver factory
│   │   │   ├── FirefoxBrowserFactory.java    # Firefox WebDriver factory
│   │   │   ├── Config.java                   # Centralized config (URL, timeout, headless)
│   │   │   └── LoggingDriverDecorator.java   # Decorator — logs all WebDriver actions
│   │   ├── pages/
│   │   │   ├── BasePage.java                 # Abstract base — holds WebDriver + goTo() helper
│   │   │   ├── LoginPage.java                # Login page object
│   │   │   └── ProductsPage.java             # Products/dashboard page object
│   │   └── utils/
│   │       └── WaitUtils.java                # Explicit wait wrapper (WebDriverWait)
│   └── test/
│       ├── java/com/saucedemo/
│       │   ├── hooks/
│       │   │   ├── chrome/
│       │   │   │   └── ChromeHooks.java      # @Before/@After — sets browser=chrome
│       │   │   └── firefox/
│       │   │       └── FirefoxHooks.java      # @Before/@After — sets browser=firefox
│       │   ├── runners/
│       │   │   ├── ChromeRunnerTest.java      # JUnit 5 suite — Chrome
│       │   │   └── FirefoxRunnerTest.java     # JUnit 5 suite — Firefox
│       │   └── tests/
│       │       └── LoginSteps.java            # Cucumber step definitions
│       └── resources/
│           ├── features/
│           │   └── login.feature              # Gherkin scenarios (UC-1, UC-2, UC-3)
│           ├── junit-platform.properties      # Cucumber plugin config
│           └── logback.xml                    # Logging configuration
├── pom.xml
└── README.md
```

*By Eduardo Richards*
