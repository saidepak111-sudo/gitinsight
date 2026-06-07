package com.deepak.gitinsight.Service;

import java.util.List;
import java.util.Optional;

import com.deepak.gitinsight.Dto.GithubEventDto;
import com.deepak.gitinsight.Dto.GithubRepoDto;
import com.deepak.gitinsight.Dto.GithubUserDto;

public interface GithubService {
 Optional<GithubUserDto> getGithubProfile(String username);
 List<GithubRepoDto> getGithubRepos(String username);
List<GithubEventDto> getGithubEvents(String username);


}
