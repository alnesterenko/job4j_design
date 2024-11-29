package ru.job4j.algo.scanningline;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List visitTimes) {
        int maxVisitors = 0;
        int visitorsNow = 0;
        boolean closeList = false;
        List<Integer> maxLoadHoursList = new ArrayList<>();
        List<Event> tempEventsList = createEventsList(visitTimes);
        for (Event oneEvent : tempEventsList) {
            if (oneEvent.type.equals(EventType.ARRIVAL)) {
                visitorsNow++;
            } else {
                visitorsNow--;
            }
            if (visitorsNow > maxVisitors) {
                maxVisitors = visitorsNow;
                maxLoadHoursList = new ArrayList<>();
                maxLoadHoursList.add(oneEvent.time);
                closeList = false;
            } else if (visitorsNow == maxVisitors && !closeList) {
                maxLoadHoursList.add(oneEvent.time);
            } else if ((maxVisitors - visitorsNow == 1) && !closeList) {
                maxLoadHoursList.add(oneEvent.time);
                closeList = true;
            }
        }


        return new int[]{maxLoadHoursList.get(0), maxLoadHoursList.get(maxLoadHoursList.size() - 1)};
    }

    private static List<Event> createEventsList(List visitTimes) {
        List<Event> resultEventsList = new ArrayList<>();
        for (var oneVisit : visitTimes) {
            int[] tempVisit = (int[]) oneVisit; /* Эта строка существует из-за того,
             что, так надо ))))*/
            resultEventsList.add(new Event(tempVisit[0], EventType.ARRIVAL));
            resultEventsList.add(new Event(tempVisit[1], EventType.DEPARTURE));
        }
        Collections.sort(resultEventsList);
        return resultEventsList;
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}

