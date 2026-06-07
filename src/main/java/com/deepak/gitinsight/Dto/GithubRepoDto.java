package com.deepak.gitinsight.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepoDto {
//  "name": "Hello-World",
//     "language": "Java",
//     "stargazers_count": 50,
//     "forks_count": 10
public String name;
public String language;
@JsonProperty("stargazers_count")
public int  stargazersCount;
@JsonProperty("forks_count")
public int  forksCount;

}
