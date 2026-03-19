package org.example.prototype;

import java.util.ArrayList;
import java.util.List;

// The Cloneable interface is a "Marker Interface"
public class Report implements Cloneable {
    private String header;
    private List<String> dataPoints;

    public Report(String header) {
        this.header = header;
        this.dataPoints = new ArrayList<>();
    }

    public void addData(String data) {
        this.dataPoints.add(data);
    }

    @Override
    public Report clone() {
        try {

            Report cloned = (Report) super.clone();

            // Deep copy
            cloned.dataPoints = new ArrayList<>(this.dataPoints);

            return cloned;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Report{header='" + header + "', dataPoints=" + dataPoints + "}";
    }

    // Getters and Setters
    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public List<String> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<String> dataPoints) {
        this.dataPoints = dataPoints;
    }
}