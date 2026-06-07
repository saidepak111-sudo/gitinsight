package com.deepak.gitinsight.Client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.deepak.gitinsight.Dto.GithubEventDto;
import com.deepak.gitinsight.Dto.GithubRepoDto;
import com.deepak.gitinsight.Dto.GithubUserDto;
@Component
public class GithubApiClient {
@Autowired
private RestClient restClient;

public GithubUserDto getUser(String username) {
   return restClient.get()
                    .uri("users/{username}",username)
                    .retrieve()
                    .body(GithubUserDto.class);
}
public List<GithubRepoDto> getUserRepo(String username) {
   return restClient.get()
                    .uri("/users/{username}/repos",username)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<GithubRepoDto>>() { });
}
public List<GithubEventDto> getUserEvent (String username) {
    return restClient.get()
                     .uri("users/{username}/events",username)
                     .retrieve()
                     .body(new ParameterizedTypeReference<List<GithubEventDto>>() {
                        
                     });
}
}
