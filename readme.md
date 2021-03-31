Few things to remember after:


Part 1:

00) Basic dependencies that you need for start are (artifact ids):
  - testng - executest your tests
  - slf4j-nop - solves some binding error messages in console (doesn't directly influence execution)
  - selenium-java - provides possibility for your code to interact with browser
01) Optional dependencies:
  - download-maven-plugin - in case you want to download chromedriver as part of your maven build
  - guava - in case you use selenium of version 4 or more. Required because of inner dependencies clash

1) @Test annotation marks methods as test methods for TestNG.
2) Interactions with browser are done through Webdriver instance.
3) Asserts are done by TestNG capabilities.
4) Always do driver.close() when you finish your test. Otherwise you end up with zombie chromedriver.exe (or other drivers)
processes that are not closed after your tests.



Part 2:

1) Good practice to create abstract class that is extended by test classes. It contains:
  - Setup steps common for all test classes
  - Clean up steps common for all test classes

2) TestNG annotations are a great thing, they allow to control when your test methods are executed.
  - most useful are @BeforeClass, @BeforeMethod, @AfterClass, @AfterMethod
  - allow you to avoid code duplication for some cases
  - full list can be found here - https://testng.org/doc/documentation-main.html#annotations

3) Don't hardcode locators or test data inside test methods. Use at least constants.
  - in future we will provide test data from external files and xpath will be moved out from test itself.

Part 3:

There are 2 types of asserts:
1) Hard Assert - fails test right when assertion fails. No further steps are executed.
2) Soft Assert - if assert fails, test steps are still executed afterwards.
 - if there is no "softAssert.assertAll();" at the end - test will be marked as passed.
 - in order to generate valid report without false positive result, add "softAssert.assertAll();"
 - soft asserts are useful when for example you need to check content of several elements on a page
  and want to have feedback about all of them at once, even if some fail.


Part 4:

Types of waiters:
1) Implicit - global for all driver search operation, checks page dom with certain interval (not controlled).
  Less recommended
2) Explicit:
  - Thread.sleep() - not driver method, just waits defined time interval. Not recommended
  - Wait with condition - waits for condition to be true with defined timeout and default interval.
  - Fluent wait - the same, but you define interval.
3) Don't mix Implicit and Explicit waits, as it may lead to unexpected driver behavior.

===

Part 7.1: 
1) Added dependency for browser manager.
2) You can use testng @Parameters to pass desired browser name through testng.xml
- if you want to select other browser when you execute test from command line, you can specify it value, for example 
  mvn -DbrowserName=Firefox test
- you can use @Optional - to provide default value, to be able to execute the test through IDE. 
3) Example of parallel execution in two different threads.

-- there is a problem with firefox browser, it throws errors in console, though they don't influence test execution

Part 7.2
1) Example of custom driver manager.
2) Removed parallel execution option. 
3) Small updates for logging