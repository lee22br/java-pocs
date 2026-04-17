package org.example.facade;

//Audit Logging (Chain of Custody)
public interface AuditLogger {
    void logAccess(String caseId, String evidenceId, String action, String user);
}
