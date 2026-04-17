package org.example.facade;

public class ForensicEvidence {

    private String caseId;
    private String evidenceId;
    private byte[] rawData;
    private String metadata;

    public ForensicEvidence (String caseId, String evidenceId, byte[] rawData, String metadata) {
        this.caseId = caseId;
        this.evidenceId = evidenceId;
        this.rawData = rawData;
        this.metadata = metadata;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(String evidenceId) {
        this.evidenceId = evidenceId;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
