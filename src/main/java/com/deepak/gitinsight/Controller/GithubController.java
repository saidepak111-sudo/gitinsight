package com.deepak.gitinsight.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.gitinsight.Dto.GithubEventDto;
import com.deepak.gitinsight.Dto.GithubRepoDto;
import com.deepak.gitinsight.Dto.GithubUserDto;
import com.deepak.gitinsight.Exception.UserNotFoundException;
import com.deepak.gitinsight.Model.TrackedUser;
import com.deepak.gitinsight.Service.GithubService;
import com.deepak.gitinsight.Service.TrackedUserService;
import com.deepak.gitinsight.Service.UserSummaryService;


@RestController
public class GithubController {
    @Autowired
    private GithubService githubService;
    @Autowired
    private TrackedUserService trackedUserService;
    @Autowired
    private UserSummaryService userSummaryService;
 @GetMapping ("/api/github/users/{username}")
 public ResponseEntity<GithubUserDto> getUserProfile(@PathVariable String username) {
    GithubUserDto user=githubService.getGithubProfile(username).orElseThrow( ()->  new UserNotFoundException("You Requested is not found with username "+username));

    
 return new ResponseEntity<>(user,HttpStatus.OK);
 }
 @GetMapping("api/github/users/{username}/repos")
public ResponseEntity<List<GithubRepoDto>> getUserRepositories(@PathVariable String username)  {
    return new ResponseEntity<>(githubService.getGithubRepos(username),HttpStatus.OK);
}
 @GetMapping("api/github/users/{username}/events")
public ResponseEntity<List<GithubEventDto>> getUserEvents(@PathVariable String username)  {
    return new ResponseEntity<>(githubService.getGithubEvents(username),HttpStatus.OK);
}
@PostMapping("/api/tracked-users/{username}")
public ResponseEntity<TrackedUser> trackUser(@PathVariable String username) {
    return new ResponseEntity<>(trackedUserService.addUser(username), HttpStatus.CREATED);
}
@GetMapping ("/api/tracked-users")
public ResponseEntity<List<TrackedUser>> getAlltrackedUsers() {
    return new ResponseEntity<>(trackedUserService.getAllTrackedUsers(),HttpStatus.OK);
}
@GetMapping("/api/tracked-users/{username}") 
public ResponseEntity<TrackedUser> getTrackedUserByUsername(@PathVariable String username) {
    return new ResponseEntity<>(trackedUserService.getTrackedUserByUsername(username),HttpStatus.OK);
}
@DeleteMapping("/api/tracked-users/{username}") 
public ResponseEntity<Void> deleteTrackedUserByUsername(@PathVariable String username) {
    trackedUserService.deleteTrackedUser(username);
    return new ResponseEntity<>(HttpStatus.OK);
}
@GetMapping("api/github/users/{username}/summary")
public ResponseEntity<?> getUsernameSummary(@PathVariable String username) {
  return new ResponseEntity<>(userSummaryService.getUserSummary(username),HttpStatus.OK);
}
}


