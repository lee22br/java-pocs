package org.example.composite;

class SimpleTask implements Task {
    private final String title;
    private final int duration;

    public SimpleTask(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public int getDurationInMinutes() { return duration; }
}
