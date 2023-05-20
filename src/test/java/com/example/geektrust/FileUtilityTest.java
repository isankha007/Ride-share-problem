package com.example.geektrust;

import com.example.geektrust.constants.CommandTypes;
import com.example.geektrust.utility.CommandParams;
import com.example.geektrust.utility.FileReaderUtility;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileUtilityTest {
    private FileReaderUtility fileReaderUtility;

    @BeforeEach
    void setUp(){
        fileReaderUtility=new FileReaderUtility();
        fileReaderUtility.setFilePath("sample_input/input1.txt");
    }

    @Test
    void getCommandParamsTest(){
        List<CommandParams> commandParams = fileReaderUtility.getCommandParams();
        Assert.assertEquals(CommandTypes.ADD_DRIVER.getType(),commandParams.get(0).getCommand());

    }
}
