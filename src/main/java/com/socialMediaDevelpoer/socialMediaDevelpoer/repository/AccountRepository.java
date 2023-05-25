package com.socialMediaDevelpoer.socialMediaDevelpoer.repository;

import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<UserAccount,Long> {
    UserAccount findByEmail(String email);
    UserAccount findById(String id);
}
