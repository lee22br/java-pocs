package org.example.prototype;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    @DisplayName("Should create a deep copy that does not affect the original")
    void testClassicalCloneDeepCopy() {
        Report original = new Report("Forensic Analysis v1");
        original.addData("Metadata: Initial Scan");
        original.addData("Status: Pending");

        Report clone = original.clone();

        assertNotSame(original, clone, "The clone should be a different object instance");
        assertEquals(original.toString(), clone.toString(), "Content should be identical initially");

        clone.addData("Metadata: Added by Clone");
        clone.setHeader("Forensic Analysis v2");


        // Check Header
        assertEquals("Forensic Analysis v1", original.getHeader(),
                "Original header should remain unchanged");

        // Check List Size
        assertEquals(2, original.getDataPoints().size(),
                "Original list should not contain the data added to the clone");

        assertEquals(3, clone.getDataPoints().size(),
                "Clone list should reflect its own changes");

        // Check List Content
        assertFalse(original.getDataPoints().contains("Metadata: Added by Clone"),
                "Original list should NOT contain the clone's new data");

        assertTrue(clone.getDataPoints().contains("Metadata: Added by Clone"),
                "Clone list SHOULD contain its own new data");
    }
}