package com.znsio.sample.e2e.entities;

import com.znsio.e2e.entities.TEST_CONTEXT;
import org.mockito.internal.util.Platform;

import java.util.logging.Logger;

public class Amazon  extends TEST_CONTEXT {

    private static final Logger LOGGER = Logger.getLogger(Amazon.class.getName());
    private  final String username;
    private  final String password;

    public static final String ME = "me";

    public Amazon (String userpersona, Platform onPlatform) {
        this.username = "sdhcdh@gmail.com";
        this.password = "fdvdfvdfvdv";
    }


    public static String getUsername() {
        return "raghavgarg@gmail.vom";
    }

    public static String getPassword() {
        return "fjdvdbfv";
    }
}
