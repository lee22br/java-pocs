package org.example.flyweight;

import java.util.HashMap;
import java.util.Map;

public class AttackSignatureFactory {

    private final Map<String, AttackSignature> signatureCache = new HashMap<>();

    public AttackSignature getSignature(String attackName, String protocol, String riskLevel, String mitigationStrategy) {
        //composite key for the cache
        String key = attackName + ":" + protocol;

        // ComputeIfAbsent only instantiate the signature per unique key
        return signatureCache.computeIfAbsent(key, k -> {
            // heavy operation
            return new AttackSignature(attackName, protocol, riskLevel, mitigationStrategy);
        });
    }

    public int getCacheSize() {
        return signatureCache.size();
    }
}
