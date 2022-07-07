package assignments0707;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MySongCache implements SongCache{
    public ConcurrentHashMap<String, Integer> cache;
    public MySongCache() {
        this.cache = new ConcurrentHashMap<>();
    }
    // Record number of plays for a song
    @Override
    public void recordSongPlays(String songId, int numPlays) {
        this.cache.put(songId, cache.getOrDefault(songId,0)+numPlays);
    }

    // Fetch the number of plays for a song
    // return the number of plays, or -1 if the song ID is unknown
    @Override
    public int getPlaysForSong(String songId) {
        return this.cache.getOrDefault(songId, -1);
    }

    // Return the top N songs played, in descending order of number of plays
    @Override
    public List<String> getTopNSongsPlayed(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) {
            return list;
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (Map.Entry<String, Integer> e : this.cache.entrySet()) {
            if (pq.size() < n) {
                pq.offer(e);
            } else {
                if (e.getValue() > pq.peek().getValue()) {
                    pq.poll();
                    pq.offer(e);
                }
            }
        }
        while (!pq.isEmpty()) {
            list.add(0,pq.poll().getKey());
        }
        return list;
    }

    public static void main(String[] args) {
        MySongCache sc = new MySongCache();
        sc.recordSongPlays("ID-1", 3);
        sc.recordSongPlays("ID-1", 1);
        sc.recordSongPlays("ID-2", 2);
        sc.recordSongPlays("ID-3", 5);
        System.out.println(sc.getPlaysForSong("ID-1"));
        System.out.println(sc.getPlaysForSong("ID-9"));
        System.out.println(sc.getTopNSongsPlayed(2));
        System.out.println(sc.getTopNSongsPlayed(0));
    }
}
