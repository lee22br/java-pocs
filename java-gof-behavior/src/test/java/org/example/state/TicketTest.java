package org.example.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket("JIRA-1024");
    }

    @Test
    void testHappyPath_FullLifecycle() {
        assertEquals("NEW", ticket.getStateName());

        ticket.startProgress();
        assertEquals("IN_PROGRESS", ticket.getStateName());

        ticket.resolve();
        assertEquals("RESOLVED", ticket.getStateName());

        ticket.close();
        assertEquals("CLOSED", ticket.getStateName());
    }

    @Test
    void testReopenResolvedTicket() {
        ticket.startProgress();
        ticket.resolve();
        assertEquals("RESOLVED", ticket.getStateName());

        // QA found a bug
        ticket.startProgress();

        assertEquals("IN_PROGRESS", ticket.getStateName(),
                "Ticket should transition back to IN_PROGRESS from RESOLVED");
    }

    @Test
    void testInvalidTransition_CloseNewTicket() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            ticket.close();
        });

        assertTrue(exception.getMessage().contains("Cannot close from NEW"));
        assertEquals("NEW", ticket.getStateName(), "State must remain unchanged.");
    }

    @Test
    void testInvalidTransition_ModifyClosedTicket() {
        ticket.startProgress();
        ticket.resolve();
        ticket.close();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            ticket.startProgress();
        });

        assertTrue(exception.getMessage().contains("Cannot start progress from CLOSED"));
    }
}
