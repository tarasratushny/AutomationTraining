package com.miamato;

import java.util.Arrays;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    public static void logStackTrace(Throwable e, Logger logger){
        Arrays.stream(Arrays.stream(e.getStackTrace()).toArray()).forEach(logger::error);
    }

}
