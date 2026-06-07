package com.deepak.gitinsight.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubEventDto {
private String id;

private String type;

private GithubEventRepoDto repo;

@JsonProperty("created_at")
private String createdAt;
}
