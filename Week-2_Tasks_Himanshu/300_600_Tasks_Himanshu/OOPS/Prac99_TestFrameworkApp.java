import java.util.ArrayList;
import java.util.List;

class TestCase {
    private final String name;
    private final Runnable testFunction;
    private boolean isSuccess;

    public TestCase(String name, Runnable testFunction) {
        this.name = name;
        this.testFunction = testFunction;
        this.isSuccess = false;
    }

    public void run() {
        try {
            testFunction.run();
            isSuccess = true;
        } catch (Exception e) {
            System.err.println("Test Case Failed: " + name + "\n  Exception: " + e.getMessage());
            isSuccess = false;
        }
    }

    public boolean getResult() {
        return isSuccess;
    }
}

class TestSuite {
    private final List<TestCase> testCases;

    public TestSuite() {
        this.testCases = new ArrayList<>();
    }

    public void addTestCase(String name, Runnable testFunction) {
        testCases.add(new TestCase(name, testFunction));
    }

    public void run() {
        for (TestCase testCase : testCases) {
            testCase.run();
        }
    }

    public void printResults() {
        System.out.println("\nTest Results:\n----------------");
        for (TestCase testCase : testCases) {
            System.out.println("Test Case: " + testCase.getResult() + " - " +
                    (testCase.getResult() ? "Passed" : "Failed"));
        }
        System.out.println("----------------");
    }
}

class TestRunner {
    public static void runTests(TestSuite testSuite) {
        testSuite.run();
        testSuite.printResults();
    }
}

public class Prac99_TestFrameworkApp {
    // Sample test cases
    public static void testAddition() {
        TestCase testCase = new TestCase("Test Addition", () -> {
            int result = 1 + 1;
            if (result != 2) {
                throw new RuntimeException("Addition failed!");
            }
        });

        testCase.run();
    }

    public static void testSubtraction() {
        TestCase testCase = new TestCase("Test Subtraction", () -> {
            int result = 3 - 1;
            if (result != 2) {
                throw new RuntimeException("Subtraction failed!");
            }
        });

        testCase.run();
    }

    public static void testMultiplication() {
        TestCase testCase = new TestCase("Test Multiplication", () -> {
            int result = 2 * 3;
            if (result != 6) {
                throw new RuntimeException("Multiplication failed!");
            }
        });

        testCase.run();
    }

    public static void main(String[] args) {
        TestSuite testSuite = new TestSuite();
        testSuite.addTestCase("Addition", Prac99_TestFrameworkApp::testAddition);
        testSuite.addTestCase("Subtraction", Prac99_TestFrameworkApp::testSubtraction);
        testSuite.addTestCase("Multiplication", Prac99_TestFrameworkApp::testMultiplication);

        TestRunner.runTests(testSuite);
    }
}
