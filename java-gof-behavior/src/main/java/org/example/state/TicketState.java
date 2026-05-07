package org.example.state;

public interface TicketState {

    default void startProgress(Ticket ticket) {
        throw new IllegalStateException("Transition not allowed: Cannot start progress from " + getStateName());
    }

    default void resolve(Ticket ticket) {
        throw new IllegalStateException("Transition not allowed: Cannot resolve from " + getStateName());
    }

    default void close(Ticket ticket) {
        throw new IllegalStateException("Transition not allowed: Cannot close from " + getStateName());
    }

    String getStateName();
}
