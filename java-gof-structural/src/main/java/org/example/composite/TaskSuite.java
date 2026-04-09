package org.example.composite;

import java.util.ArrayList;
import java.util.List;

class TaskSuite implements Task {
    private final String title;
    private final List<Task> subTasks = new ArrayList<>();

    public TaskSuite(String title) {
        this.title = title;
    }

    public void addTask(Task task) {
        subTasks.add(task);
    }

    public void removeTask(Task task) {
        subTasks.remove(task);
    }

    @Override
    public String getTitle() { return title; }

    /**
     * Composition uniformly by summing up
     * durations of all children, regardless of their type.
     */
    @Override
    public int getDurationInMinutes() {
        return subTasks.stream()
                .mapToInt(Task::getDurationInMinutes)
                .sum();
    }
}
