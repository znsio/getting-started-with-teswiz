package com.znsio.sample.e2e.entities;

import com.znsio.e2e.entities.TEST_CONTEXT;
import org.mockito.internal.util.Platform;

public class SAMPLE_TEST_CONTEXT
        extends TEST_CONTEXT {
    public static final String ME = "me";
    public static final String MEETING_ID = "meetingId";
    public static final String MEETING_PASSWORD = "meetingPassword";
    public static final String INVITATION_LINK = "invitationLink";

    private  final String username;
    private  final String password;

    public SAMPLE_TEST_CONTEXT(String userpersona, Platform onPlatform) {
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
