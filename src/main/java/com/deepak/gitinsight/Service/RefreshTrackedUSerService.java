package com.deepak.gitinsight.Service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.deepak.gitinsight.Model.TrackedUser;
import com.deepak.gitinsight.Repository.TrackedUserRepository;


@Service
public class RefreshTrackedUSerService {
    private final TrackedUserService trackedUserService;
    private final TrackedUserRepository trackedUserRepository;
    public RefreshTrackedUSerService(TrackedUserService trackedUserService, TrackedUserRepository trackedUserRepository) {
        this.trackedUserService = trackedUserService;
        this.trackedUserRepository=trackedUserRepository;
    }
 @Async("taskExecutor")
@Scheduled(fixedRate = 30000) 
    public void refreshAllTrackedUsersData() {
        List<TrackedUser> trackedUsers = trackedUserRepository.findAll();
        for (TrackedUser user : trackedUsers) {
           trackedUserService.refreshTrackedUserData(user.getGithubUsername());
        }
    }
}
