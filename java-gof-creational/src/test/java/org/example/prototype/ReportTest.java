package org.example.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class ReportTest {

    @Test
    @DisplayName("Should create a deep copy do not affect the original")
    void testClassicalCloneDeepCopy() {
        Report original = new Report("Forensic Analysis v1");
        original.addData("Metadata: Initial Scan");
        original.addData("Status: Pending");

        Report clone = original.clone();

        assertNotSame(original, clone, "The clone should be a different object instance");

        // same data
        assertThat(clone.toString()).isEqualTo(original.toString());

        // change clone data
        clone.addData("Metadata: Added by Clone");
        clone.setHeader("Forensic Analysis v2");

        // If this fails, it means was performed a Shallow Copy!
        assertThat(original.setHeader()).isEqualTo("Forensic Analysis v1");
        assertThat(original.getDataPoints())
                .as("Original list should not contain the data added to the clone")
                .hasSize(2)
                .doesNotContain("Metadata: Added by Clone");

        assertThat(clone.getDataPoints())
                .as("Clone list should reflect its own changes")
                .hasSize(3)
                .contains("Metadata: Added by Clone");
    }
}