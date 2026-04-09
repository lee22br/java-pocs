package org.example.composite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSuiteTest {

    @Test
    @DisplayName("Should calculate total duration for a complex nested task hierarchy")
    void testComplexTaskHierarchy() {
        // Simple Tasks
        Task coding = new SimpleTask("Coding Feature X", 120);
        Task testing = new SimpleTask("Unit Testing", 60);

        //TaskSuite
        TaskSuite developmentSuite = new TaskSuite("Development Phase");
        developmentSuite.addTask(coding);
        developmentSuite.addTask(testing);

        // Docs and Project core
        Task documentation = new SimpleTask("Technical Docs", 30);
        TaskSuite projectAlpha = new TaskSuite("Project Alpha");

        projectAlpha.addTask(developmentSuite); // Adding a composite to a composite
        projectAlpha.addTask(documentation);    // Adding a leaf to a composite

        // Assert
        // 120 (coding) + 60 (testing) + 30 (docs) = 210
        assertEquals(210, projectAlpha.getDurationInMinutes(),
                "The total duration should be the sum of all sub-tasks and sub-suites.");
    }

    @Test
    @DisplayName("Should handle empty suites correctly")
    void testEmptySuite() {
        TaskSuite emptySuite = new TaskSuite("Empty");
        assertEquals(0, emptySuite.getDurationInMinutes());
    }
}
