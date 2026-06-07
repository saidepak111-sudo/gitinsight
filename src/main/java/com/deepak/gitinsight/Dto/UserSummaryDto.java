package com.deepak.gitinsight.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDto {
private String githubUsername;
private int followers;
private int following;
private int publicRepos;
private Long totalStars;
private Long totalForks;
private String topLanguage;
private int recentActivityCount;
}
