package com.deepak.gitinsight.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deepak.gitinsight.Client.GithubApiClient;
import com.deepak.gitinsight.Dto.GithubEventDto;
import com.deepak.gitinsight.Dto.GithubRepoDto;
import com.deepak.gitinsight.Dto.GithubUserDto;
@Service
public class GithubServiceImpl  implements GithubService{
    @Autowired
    private GithubApiClient githubApiClient;
   @Override
@Cacheable(value = "github-user", key = "#username")
public Optional<GithubUserDto> getGithubProfile(String username) {
    System.out.println("Chache is not stored...!");
    return Optional.ofNullable(githubApiClient.getUser(username));
}

@Override
@Cacheable(value = "github-repos", key = "#username")
public List<GithubRepoDto> getGithubRepos(String username) {
    return githubApiClient.getUserRepo(username);
}
@Override
@Cacheable(value = "github-events", key = "#username")
public List<GithubEventDto> getGithubEvents(String username) {
    return githubApiClient.getUserEvent(username);
}

}
