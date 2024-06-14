package com.notbronwyn.OldGL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class Output {
    private static final Logger log = LogManager.getLogger("OldGL");
    public static void print(String a) {
        log.log(Level.INFO,a);
    }
}
