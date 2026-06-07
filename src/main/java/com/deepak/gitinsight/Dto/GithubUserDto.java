package com.deepak.gitinsight.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubUserDto {
private String id;
private String login;
private String name;

@JsonProperty("avatar_url")
private String avatarUrl;

@JsonProperty("public_repos")
private int publicRepos;

private int followers;
private int following;
}
