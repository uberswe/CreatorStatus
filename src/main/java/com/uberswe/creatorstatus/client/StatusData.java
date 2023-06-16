package com.uberswe.creatorstatus.client;
import com.uberswe.creatorstatus.CreatorStatus;
import com.uberswe.creatorstatus.event.ModEvents;

import java.util.HashMap;

public class StatusData {

    private static HashMap<String, Integer> statuses;
    public static void set(HashMap<String, CreatorStatus.Status> statuses) {
        StatusData.statuses = statuses;
    }
    public static void setPlayerStatus(String uuid, CreatorStatus.Status playerStatus) {
        StatusData.statuses.put(uuid, playerStatus);
    }
    public static CreatorStatus.Status getPlayerStatus(String uuid) {
        if (StatusData.statuses.get(uuid) != null) {
            return StatusData.statuses.get(uuid);
        }
        return CreatorStatus.Status.NONE;
    }

    public static HashMap<String, CreatorStatus.Status> getStatuses() {
        if (statuses == null) {
            statuses = new HashMap<>();
        }
        return statuses;
    }

}
