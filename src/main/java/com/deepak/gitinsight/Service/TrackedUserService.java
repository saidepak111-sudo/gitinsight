package com.deepak.gitinsight.Service;

import java.util.List;

import com.deepak.gitinsight.Model.TrackedUser;

public interface TrackedUserService {
public TrackedUser addUser(String username);
List<TrackedUser> getAllTrackedUsers();

TrackedUser getTrackedUserByUsername(String username);

void deleteTrackedUser(String username);
void refreshTrackedUserData(String username);
}
