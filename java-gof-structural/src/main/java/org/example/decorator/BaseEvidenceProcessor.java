package org.example.decorator;

public class BaseEvidenceProcessor implements EvidenceProcessor {

        public String process(String data) {
            return data;
        }

        public String restore(String data) {
            return data;
        }
}
