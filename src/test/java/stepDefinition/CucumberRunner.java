//package stepDefinition;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.DataProvider;
//
//@CucumberOptions(
//        features = {"src/test/resources/features/Login.feature"}
//)
//
//public class CucumberRunner extends AbstractTestNGCucumberTests{
//
//    public TestNGCucumberRunner testNGCucumberRunner;
//
//    @BeforeClass(alwaysRun = true)
//    public static void setUpCucumber() {
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//    }
//
//    @DataProvider
//    public Object[][] features() {
//        return testNGCucumberRunner.provideScenarios();
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDownClass() {
//        testNGCucumberRunner.finish();
//    }
//}
