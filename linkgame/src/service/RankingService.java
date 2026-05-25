package service;

import model.RankingEntry;
import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class RankingService {
    private UserService userService;

    public RankingService() {
        userService = new UserService();
    }

    public List<RankingEntry> getTopRankings(int limit) {
        Map<String, User> users = userService.getAllUsers();
        List<RankingEntry> entries = new ArrayList<>();
        for (User user : users.values()) {
            entries.add(new RankingEntry(user.getUsername(), user.getHighScore(), 1));
        }
        entries.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        if (entries.size() > limit) {
            return entries.subList(0, limit);
        }
        return entries;
    }
}
