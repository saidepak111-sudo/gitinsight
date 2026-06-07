package com.deepak.gitinsight.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.gitinsight.Dto.GithubUserDto;
import com.deepak.gitinsight.Repository.TrackedUserRepository;
import com.deepak.gitinsight.Exception.*;
import com.deepak.gitinsight.Model.TrackedUser;

@Service
public class TrackedUserServiceImpl implements TrackedUserService {

    @Autowired
    private TrackedUserRepository trackedUserRepository;

    @Autowired
    private GithubService githubService;

    @Override
    public TrackedUser addUser(String username) {
          
         if (trackedUserRepository.existsByGithubUsername(username)) {
            throw new UserAlreadyTrackedException("User already Present with this Username "+ username);
         }
        GithubUserDto user = githubService.getGithubProfile(username).orElseThrow(()->new UserNotFoundException("You Requested is not found with username "+username));

        TrackedUser newUser = new TrackedUser();

        newUser.setGithubId(user.getId());
        newUser.setGithubUsername(user.getLogin());
        newUser.setName(user.getName());
        newUser.setAvatarUrl(user.getAvatarUrl());
        newUser.setPublicRepos(user.getPublicRepos());
        newUser.setFollowers(user.getFollowers());
        newUser.setFollowing(user.getFollowing());
        newUser.setTrackedAt(LocalDate.now());
        return trackedUserRepository.save(newUser);
    }

    @Override
    public List<TrackedUser> getAllTrackedUsers() {
        return trackedUserRepository.findAll();
    }

    @Override
    public TrackedUser getTrackedUserByUsername(String username) {
        return trackedUserRepository.findByGithubUsername(username).orElseThrow(()->new UserNotFoundException("You Requested is not found with username "+username));
    }

    @Override
    @Transactional
    public void deleteTrackedUser(String username) {
        if (!trackedUserRepository.existsByGithubUsername(username)) {
            throw new UserNotFoundException("You Requested is not found with username "+username);

         }
       trackedUserRepository.deleteByGithubUsername(username);
    }
    @Async("taskExecutor")
    @Override
    public void refreshTrackedUserData(String username) {
        TrackedUser existingUser = trackedUserRepository.findByGithubUsername(username).orElseThrow(()->new UserNotFoundException("You Requested is not found with username "+username));

        GithubUserDto user = githubService.getGithubProfile(username).orElseThrow(()->new UserNotFoundException("You Requested is not found with username "+username));

        existingUser.setName(user.getName());
        existingUser.setAvatarUrl(user.getAvatarUrl());
        existingUser.setPublicRepos(user.getPublicRepos());
        existingUser.setFollowers(user.getFollowers());
        existingUser.setFollowing(user.getFollowing());
        existingUser.setTrackedAt(LocalDate.now());

        trackedUserRepository.save(existingUser);
    }
   
 
    
}