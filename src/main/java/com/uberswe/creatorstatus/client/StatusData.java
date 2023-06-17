package com.uberswe.creatorstatus.client;
import com.uberswe.creatorstatus.CreatorStatus;
import com.uberswe.creatorstatus.event.ModEvents;

import java.util.HashMap;

public class StatusData {

    private static HashMap<String, Integer> statuses;
    public static void set(HashMap<String, Integer> statuses) {
        StatusData.statuses = statuses;
    }
    public static void setPlayerStatus(String uuid, int playerStatus) {
        StatusData.statuses.put(uuid, playerStatus);
    }
    public static int getPlayerStatus(String uuid) {
        if (StatusData.statuses != null && StatusData.statuses.get(uuid) != null) {
            return StatusData.statuses.get(uuid);
        }
        return CreatorStatus.NONE;
    }

    public static HashMap<String, Integer> getStatuses() {
        if (statuses == null) {
            statuses = new HashMap<>();
        }
        return statuses;
    }

}
