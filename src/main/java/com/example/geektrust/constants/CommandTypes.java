package com.example.geektrust.constants;

public enum CommandTypes {
    ADD_DRIVER("ADD_DRIVER"),
    ADD_RIDER("ADD_RIDER"),
    MATCH("MATCH"),
    START_RIDE("START_RIDE"),
    STOP_RIDE("STOP_RIDE"),
    BILL("BILL");

    private final String type;

    CommandTypes(String typeStr) {
        this.type=typeStr;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
