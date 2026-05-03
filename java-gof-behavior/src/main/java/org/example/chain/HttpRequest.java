package org.example.chain;

public record HttpRequest(String apiKey, String ipAddress, String path) {}