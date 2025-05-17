package tut.scp.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IssueCategory {
    FACILITIES("Facilities & Infrastructure"),
    IT_SUPPORT("IT & Technical Support"),
    SMART_SYSTEMS("Smart Systems / IoT Devices"),
    CLEANING("Cleaning & Sanitation"),
    SAFETY("Safety & Security"),
    SOFTWARE("Software & Portal Issues");

    private final String displayName;

    IssueCategory(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
