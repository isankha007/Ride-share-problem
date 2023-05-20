package com.example.geektrust.utility;

import java.util.List;

public class CommandParams {
    private String command;
    private List<String> tokens;

    public CommandParams(String command, List<String> tokens) {
        this.command = command;
        this.tokens = tokens;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "CommandUtility{" +
                "command='" + command + '\'' +
                ", tokens=" + tokens +
                '}';
    }
}
