package com.example.geektrust.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderUtility {
    private String filePath;

    public FileReaderUtility() {
    }

    public FileReaderUtility(String filePath) {
        this.filePath = filePath;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<CommandParams> getCommandParams()  {
        try(Stream<String> lines=Files.lines(Paths.get(this.filePath))){
            return lines.map(this::processLines).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CommandParams processLines(String line){
            String[] commands=line.split(" ");
            List<String> tokens= Arrays.stream(commands).sequential().skip(1).map(String::trim).collect(Collectors.toList());
            return new CommandParams(commands[0],tokens);
    }
}
