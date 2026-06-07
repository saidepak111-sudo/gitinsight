package com.deepak.gitinsight.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.gitinsight.Model.TrackedUser;

public interface TrackedUserRepository  extends JpaRepository<TrackedUser,Long> {


    boolean existsByGithubUsername(String username);

    Optional<TrackedUser> findByGithubUsername(String username);
    void deleteByGithubUsername(String username);

}
