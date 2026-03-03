package com.znsio.sample.e2e.businessLayer.heartbeat;

import com.znsio.teswiz.tools.HeartBeat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeartBeatBL {
    private static final int HEARTBEAT_INTERVAL_SECONDS = 30;
    private static final String RUN_CONTEXT = "teswiz";
    private static final Map<String, HeartBeat> ACTIVE_HEARTBEATS = new ConcurrentHashMap<>();

    public void startHeatBeat(String userPersona) {
        HeartBeat existing = ACTIVE_HEARTBEATS.remove(userPersona);
        if (existing != null) {
            existing.stopHeartBeat();
        }

        HeartBeat heartBeat = new HeartBeat(userPersona, RUN_CONTEXT, HEARTBEAT_INTERVAL_SECONDS);
        ACTIVE_HEARTBEATS.put(userPersona, heartBeat);
        Thread thread = new Thread(heartBeat, "heartbeat-" + userPersona);
        thread.setDaemon(true);
        thread.start();
    }

    public void stopHeartBeat(String userPersona) {
        HeartBeat heartBeat = ACTIVE_HEARTBEATS.remove(userPersona);
        if (heartBeat != null) {
            heartBeat.stopHeartBeat();
        }
    }
}
