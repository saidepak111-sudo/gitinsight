package com.deepak.gitinsight.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deepak.gitinsight.Dto.GithubRepoDto;
import com.deepak.gitinsight.Dto.GithubUserDto;
import com.deepak.gitinsight.Dto.UserSummaryDto;
import com.deepak.gitinsight.Exception.UserNotFoundException;

@Service
public class UserSummaryServiceImpl implements UserSummaryService {

    @Autowired
    private GithubService githubService;
    @Cacheable(
    value = "github-summary",
    key = "#username"
)
    @Override
    public UserSummaryDto getUserSummary(String username) {

        GithubUserDto user = githubService.getGithubProfile(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with username " + username
                ));

        List<GithubRepoDto> repos = githubService.getGithubRepos(username);

        UserSummaryDto newUser = new UserSummaryDto();

        newUser.setGithubUsername(user.getLogin());
        newUser.setFollowers(user.getFollowers());
        newUser.setFollowing(user.getFollowing());
        newUser.setPublicRepos(user.getPublicRepos());

        long totalForks = repos.stream()
                .mapToLong(GithubRepoDto::getForksCount)
                .sum();

        long totalStars = repos.stream()
                .mapToLong(GithubRepoDto::getStargazersCount)
                .sum();

        int recentActivityCount = githubService.getGithubEvents(username).size();

        String topLanguage = findTopLanguage(repos);

        newUser.setTotalForks(totalForks);
        newUser.setTotalStars(totalStars);
        newUser.setRecentActivityCount(recentActivityCount);
        newUser.setTopLanguage(topLanguage);

        return newUser;
    }

    private String findTopLanguage(List<GithubRepoDto> repos) {

        Map<String, Integer> languageCount = new HashMap<>();

        for (GithubRepoDto repo : repos) {
            String language = repo.getLanguage();

            if (language != null) {
                languageCount.merge(language, 1, Integer::sum);
            }
        }

        if (languageCount.isEmpty()) {
            return "Not Available";
        }

        return Collections.max(
                languageCount.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();
    }
}