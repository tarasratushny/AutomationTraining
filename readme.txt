Few things to remember after part 1:

00) Basic dependencies that you need for start are (artifact ids):
  testng - executest your tests
  slf4j-nop - solves some binding error messages in console (doesn't directly influence execution)
  selenium-java - provides possibility for your code to interact with browser
01) Optional dependencies:
  download-maven-plugin - in case you want to download chromedriver as part of your maven build
  guava - in case you use selenium of version 4 or more. Required because of inner dependencies clash

1) @Test annotation marks methods as test methods for TestNG.
2) Interactions with browser are done through Webdriver instance.
3) Asserts are done by TestNG capabilities.
4) Always do driver.close() when you finish your test. Otherwise you end up with zombie chromedriver.exe (or other drivers)
processes that are not closed after your tests.