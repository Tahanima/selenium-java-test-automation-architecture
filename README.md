# Selenium Test Automation Boilerplate

Ready-to-use UI Test Automation Architecture using Java and Selenium WebDriver. 

## Installation Steps

In order to use the framework:

1. [Fork](https://github.com/Tahanima/selenium-test-automation-boilerplate/fork) the repository.
2. Clone, i.e, download your copy of the repository to your local machine using
```
git clone https://github.com/[your_username]/selenium-test-automation-boilerplate.git
```
3. Import the project in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
4. Make your desired changes.
5. Use IntelliJ IDEA to run your desired tests. Alternatively, you can use the terminal to run the tests, for example `./gradlew test -Dbrowser=firefox -Dheadless=false` to run all the tests using the firefox browser in headed mode.

## Languages and Frameworks

The project uses the following:
- *[Java 11](https://openjdk.java.net/projects/jdk/11/)* as the programming language.
- *[Selenium WebDriver](https://www.selenium.dev/)* as the web browser automation framework using the Java binding.
- *[WebDriverManager](https://bonigarcia.dev/webdrivermanager/)* as the browser driver management library.
- *[Univocity Parsers](https://www.univocity.com/pages/univocity_parsers_tutorial)* to parse and handle CSV files.
- *[TestNG](https://testng.org/doc/)* as the testing framework.
- *[AssertJ](https://assertj.github.io/doc/)* as the assertion library.
- *[Lombok](https://projectlombok.org/)* to generate getters.
- *[Owner](http://owner.aeonbits.org/)* to minimize the code to handle properties file.
- *[Extent Reports](https://www.extentreports.com/)* as the test reporting strategy.
- *[Selenium Shutterbug](https://github.com/assertthat/selenium-shutterbug)* for capturing screenshots.
- *[Gradle](https://gradle.org/)* as the Java build tool.
- *[IntelliJ IDEA](https://www.jetbrains.com/idea/)* as the IDE.

## Project Structure

The project is structured as follows:

```bash
📦 selenium-test-automation-boilerplate
├─ .github
│  └─ workflows
│     └─ test-execution.yml
├─ .gitignore
├─ README.md
├─ build.gradle
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ script
│  └─ install_chrome.sh
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ io
   │  │     └─ github
   │  │        └─ tahanima
   │  │           ├─ config
   │  │           │  ├─ Configuration.java
   │  │           │  ├─ ConfigurationManager.java
   │  │           │  └─ package-info.java
   │  │           ├─ data
   │  │           │  ├─ BaseData.java
   │  │           │  ├─ login
   │  │           │  │  └─ LoginData.java
   │  │           │  └─ package-info.java
   │  │           ├─ driver
   │  │           │  ├─ BrowserFactory.java
   │  │           │  ├─ DriverManager.java
   │  │           │  └─ package-info.java
   │  │           ├─ page
   │  │           │  ├─ BasePage.java
   │  │           │  ├─ BasePageFactory.java
   │  │           │  ├─ login
   │  │           │  │  └─ LoginPage.java
   │  │           │  ├─ package-info.java
   │  │           │  └─ product
   │  │           │     └─ ProductsPage.java
   │  │           └─ report
   │  │              ├─ ExtentReportManager.java
   │  │              └─ package-info.java
   │  └─ resources
   │     └─ general.properties
   └─ test
      ├─ java
      │  └─ io
      │     └─ github
      │        └─ tahanima
      │           ├─ BaseTest.java
      │           ├─ login
      │           │  └─ LoginTest.java
      │           └─ util
      │              ├─ DataProviderUtil.java
      │              └─ TestListener.java
      └─ resources
         └─ testData
            └─ login
               └─ login.csv
```

## Project Components
- [Config](#config)
- [Data](#data)
- [Driver](#driver)
- [Page](#page)
- [Report](#report)
- [Test](#test)
- [Workflow](#workflow)

### Config
The project has some global properties, for example, browser and base url. The [config](https://github.com/Tahanima/selenium-test-automation-boilerplate/blob/main/src/main/java/io/github/tahanima/config) package holds all the relevant classes to handle these global properties.

### Data
The project reads test data from csv files. The test data properties are modeled in terms of entities and the `data` package handles this. For convenience, there is an example class - [LoginData.java](src/main/java/io/github/tahanima/data/login/LoginData.java) to demonstrate the usage. 

### Driver
The project uses Selenium WebDriver to automate user workflows for web-based applications as part of automated testing. The [driver](src/main/java/io/github/tahanima/driver) package contains all the necessary initialization logic for WebDriver.

### Page
The project uses Page Object Model to capture all the relevant UI components and functionalities of a web page. The [page](src/main/java/io/github/tahanima/page) package provides all the classes to achieve this. For convenience, there is an example class - [LoginPage.java](src/main/java/io/github/tahanima/page/login/LoginPage.java) to demonstrate the usage.

### Report
The project uses *Extent Reports* to provide test reporting functionalities. The [report](src/main/java/io/github/tahanima/report) package contains the relevant class.

### Test
[LoginTest.java](src/test/java/io/github/tahanima/login/LoginTest.java) demonstrates an example test script.

### Workflow
The project uses GitHub Actions to run the selenium tests when an update is made to the `main` branch of the repo in GitHub. 
