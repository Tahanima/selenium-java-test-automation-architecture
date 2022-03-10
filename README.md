# Selenium Test Automation Boilerplate

Ready-to-use UI Test Automation Architecture using Java and Selenium WebDriver.

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
```bash
selenium-test-automation-boilerplate/
├─ .github/
│  ├─ workflows/
│  │  ├─ test-execution.yml
├─ gradle/
│  ├─ wrapper/
│  │  ├─ gradle-wrapper.jar
│  │  ├─ gradle-wrapper.properties
├─ report/
│  ├─ screenshot/
├─ script/
│  ├─ install_chrome.sh
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  ├─ io/
│  │  │  │  ├─ github/
│  │  │  │  │  ├─ tahanima/
│  │  │  │  │  │  ├─ config/
│  │  │  │  │  │  │  ├─ Configuration.java
│  │  │  │  │  │  │  ├─ ConfigurationManager.java
│  │  │  │  │  │  │  ├─ package-info.java
│  │  │  │  │  │  ├─ data/
│  │  │  │  │  │  │  ├─ login/
│  │  │  │  │  │  │  │  ├─ LoginData.java
│  │  │  │  │  │  │  ├─ BaseData.java
│  │  │  │  │  │  │  ├─ package-info.java
│  │  │  │  │  │  ├─ driver/
│  │  │  │  │  │  │  ├─ BrowserFactory.java
│  │  │  │  │  │  │  ├─ DriverManager.java
│  │  │  │  │  │  │  ├─ package-info.java
│  │  │  │  │  │  ├─ page/
│  │  │  │  │  │  │  ├─ login/
│  │  │  │  │  │  │  │  ├─ LoginPage.java
│  │  │  │  │  │  │  ├─ product/
│  │  │  │  │  │  │  │  ├─ ProductsPage.java
│  │  │  │  │  │  │  ├─ BasePage.java
│  │  │  │  │  │  │  ├─ BasePageFactory.java
│  │  │  │  │  │  │  ├─ package-info.java.java
│  │  │  │  │  │  ├─ report/
│  │  │  │  │  │  │  ├─ ReportManager.java
│  │  │  │  │  │  │  ├─ package-info.java
│  │  ├─ resources/
│  │  │  ├─ general.properties
│  ├─ test/
│  │  ├─ java/
│  │  │  ├─ io/
│  │  │  │  ├─ github/
│  │  │  │  │  ├─ tahanima/
│  │  │  │  │  │  ├─ login/
│  │  │  │  │  │  │  ├─ LoginTest.java
│  │  │  │  │  │  ├─ util/
│  │  │  │  │  │  │  ├─ DataProviderUtil.java
│  │  │  │  │  │  ├─ BaseTest.java
│  │  │  │  │  │  ├─ TestListener.java
│  │  ├─ resources/
│  │  │  ├─ testData/
│  │  │  │  ├─ login/
│  │  │  │  │  ├─ login.csv
├─ .gitignore
├─ build.gradle
├─ gradlew
├─ gradlew.bat
├─ README.md
├─ settings.gradle
```
## Project Architecture
- [Config](#config)
- [Data](#data)
- [Driver](#driver)
- [Page](#page)
- [Report](#report)
- [Test](#test)
- [Workflow](#workflow)

### Config
The project uses [general.properties](src/main/resources/general.properties) file to map all the global parameters such as browser and base url. All the relevant classes to read the parameters are provided in the [config](src/main/java/io/github/tahanima/config) package.

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
