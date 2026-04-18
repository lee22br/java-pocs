package org.example.flyweight;

public class AttackSignature {

    private String attackName;
    private String protocol;
    private String riskLevel;
    private String mitigationStrategy;


    public AttackSignature (String attackName, String protocol, String riskLevel, String mitigationStrategy) {
        this.attackName = attackName;
        this.protocol = protocol;
        this.riskLevel = riskLevel;
        this.mitigationStrategy = mitigationStrategy;
    }

    public void displayAlert(String sourceIp, String timestamp) {
        // sourceIp and timestamp are the Extrinsic State passed in at runtime
        System.out.printf("[%s] ALERT: %s attack from %s. Risk: %s. Action: %s%n",
                timestamp, attackName, sourceIp, riskLevel, mitigationStrategy);
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getMitigationStrategy() {
        return mitigationStrategy;
    }

    public void setMitigationStrategy(String mitigationStrategy) {
        this.mitigationStrategy = mitigationStrategy;
    }
}
