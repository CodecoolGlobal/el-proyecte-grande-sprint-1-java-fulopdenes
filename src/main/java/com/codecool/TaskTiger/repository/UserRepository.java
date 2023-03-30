package com.codecool.TaskTiger.repository;

import com.codecool.TaskTiger.model.WorkType;
import com.codecool.TaskTiger.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser getUserById(Long id);

    Optional<AppUser> getUserByUsername(String username);
//@Query("select a from AppUser a where ?1 in a.taskerInfo.skills and a.tasker = true ")
//    List<AppUser> findTaskersBySkills(WorkType skill);

    //List<AppUser> findAllByTaskerIsTrueAndTaskerInfoSkillsContaining(List<WorkType> skills);


}
