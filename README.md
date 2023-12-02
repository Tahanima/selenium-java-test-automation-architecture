# Selenium Java Test Automation Architecture

Ready-to-use UI Test Automation Architecture using Java and Selenium WebDriver. 

## Installation Steps

In order to use the framework:

1. [Fork](https://github.com/Tahanima/selenium-java-test-automation-architecture/fork) the repository.
2. Clone, i.e, download your copy of the repository to your local machine using
```
git clone https://github.com/[your_username]/selenium-java-test-automation-architecture.git
```
3. Import the project in [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
4. Make your desired changes.
5. Use IntelliJ IDEA to run your desired tests. Alternatively, you can use the terminal to run the tests, for example `./gradlew test -Dbrowser=firefox -Dheadless=false` to run all the tests using the firefox browser in headful mode.
6. To see the report, go to the `testoutput` folder in the project root and then go to the `report` folder.

## Languages and Frameworks

The project uses the following:

- *[Java 11](https://openjdk.java.net/projects/jdk/11/)* as the programming language.
- *[Selenium WebDriver](https://www.selenium.dev/)* as the web browser automation framework using the Java binding.
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
📦 selenium-java-test-automation-architecture
├─ .github
│  ├─ FUNDING.yml
│  ├─ dependabot.yml
│  └─ workflows
│     └─ test-execution.yml
├─ .gitignore
├─ LICENSE
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
   │  └─ java
   │     └─ io
   │        └─ github
   │           └─ tahanima
   │              ├─ config
   │              │  ├─ Configuration.java
   │              │  └─ ConfigurationManager.java
   │              ├─ dto
   │              │  ├─ BaseDto.java
   │              │  ├─ LoginDto.java
   │              │  └─ ProductsDto.java
   │              ├─ factory
   │              │  ├─ BasePageFactory.java
   │              │  └─ BrowserFactory.java
   │              ├─ report
   │              │  └─ ExtentReportManager.java
   │              └─ ui
   │                 ├─ component
   │                 │  ├─ BaseComponent.java
   │                 │  ├─ Header.java
   │                 │  └─ SideNavMenu.java
   │                 └─ page
   │                    ├─ BasePage.java
   │                    ├─ LoginPage.java
   │                    └─ ProductsPage.java
   └─ test
      ├─ java
      │  └─ io
      │     └─ github
      │        └─ tahanima
      │           ├─ e2e
      │           │  ├─ BaseTest.java
      │           │  ├─ LoginTest.java
      │           │  └─ ProductsTest.java
      │           └─ util
      │              ├─ DataProviderUtil.java
      │              ├─ TestListener.java
      │              └─ TestRetry.java
      └─ resources
         ├─ config.properties
         └─ testdata
            ├─ login.csv
            ├─ login.json
            ├─ products.csv
            └─ products.json
```

## Basic Usage

- ### Configuration
  The project uses a [*config.properties*](./src/test/resources/config.properties) file to manage global configurations such as browser type and base url.
  
  1. To add a new property, register a new entry in this file.
      ```
      key=value
      ```
    
      Then, add a method in the [*Configuration*](./src/main/java/io/github/tahanima/config/Configuration.java) interface in the below format.
      ```java
      @Key("key")
      dataType key();
      ```
    
      For example, let's say I want to add a new property named `context` with the value `dev`. In the `config.properties` file, I'll add:
      ```
      context=dev
      ```
    
      In the `Configuration` interface, I'll add:
      ```java
      @Key("context")
      String context();
      ```
    
      To use your newly created property, you need to use the below import statement.
      ```java
      import static io.github.tahanima.config.ConfigurationManager.config;
      ```
    
      Then, you can call `config().key()` to retrieve the value of your newly created property. For the example I've provided, I need to call `config().context()`.

  2. You can supply the properties present in the `config.properties` file as system properties in your test via gradle.
      ```bash
      ./gradlew test -Dkey1=value1 -Dkey2=value2
      ```
      
- ### Test Data
  The project uses *csv* or *json* file to store test data and [*univocity-parsers*](https://github.com/uniVocity/univocity-parsers) to retrieve the data and map it to a Java bean.

  To add configurations for new test data, add a new Java bean in the [*dto*](./src/main/java/io/github/tahanima/dto) package. For example, let's say I want to add test data for a `User` with the attributes `First Name` and `Last Name`. The code for this is as follows:

   ```java
   package io.github.tahanima.dto;

   import com.univocity.parsers.annotations.Parsed;

   import lombok.Getter;
   import lombok.ToString;

   @Getter
   @ToString(callSuper = true)
   public class UserDto extends BaseDto {

       @Parsed(field = "First Name", defaultNullRead = "")
       private String firstName;

       @Parsed(field = "Last Name", defaultNullRead = "")
       private String lastName;
   }
   ```
   Note that the class extends from BaseDto and thus, inherits the attributes `Test Case ID` and `Test Case Description`.

   Now, in the [*testdata*](./src/test/resources/testdata) folder you can add a csv file `user.csv` for `User` with the below contents and use it in your tests.
   ```
   Test Case ID,Test Case Description,First Name,Last Name
   TC-1,Successful user creation,Tahanima,Chowdhury
   ```
  
   Alternately, you can use a json file `user.json` with the below contents and use it in your tests.
   ```json
   [
     {
       "testCaseId": "TC-1",
       "testCaseDescription": "Successful user creation",
       "firstName": "Tahanima",
       "lastName": "Chowdhury"
     }
   ]
   ```
   For reference, check [this](./src/main/java/io/github/tahanima/dto/LoginDto.java), [this](./src/test/resources/testdata/login.csv) and [this](./src/test/java/io/github/tahanima/e2e/LoginTest.java).

- ### Browser
  The project contains the implementation of the *Chrome* and *Firefox* browsers. If you want to include an implementation of a new browser type, add the relevant codes in the [*BrowserFactory*](./src/main/java/io/github/tahanima/factory/BrowserFactory.java) enum.

  For example, let's say I want to add the `Edge` browser to the `BrowserFactory` enum. The code for this is:
  ```java
  EDGE {
      @Override
      public WebDriver getDriver() {
          WebDriver driver = new EdgeDriver(getOptions());

          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config().timeout()));
          driver.manage().window().maximize();

          return driver;
      }

      private EdgeOptions getOptions() {
          EdgeOptions options = new EdgeOptions();

          options.setAcceptInsecureCerts(true);

          if (Boolean.TRUE.equals(config().headless())) {
              options.addArguments("--headless=new");
          }

          return options;
      }
  }
  ```

  Now, you can launch all your tests in the `Edge` browser by either setting the property `browser` to `edge` in the [*config.properties*](./src/test/resources/config.properties) file or as a system property via gradle.

- ### Page Objects and Page Component Objects
  The project uses [*Page Objects* and *Page Component Objects*](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/) to capture the relevant behaviors of a web page. Check the [*ui*](./src/main/java/io/github/tahanima/ui) package for reference.

- ### Tests
  The project uses *TestNG* as the test runner. Check [this implementation](./src/test/java/io/github/tahanima/e2e/LoginTest.java) for reference.
