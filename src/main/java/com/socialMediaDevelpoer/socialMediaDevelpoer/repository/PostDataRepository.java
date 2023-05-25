package com.socialMediaDevelpoer.socialMediaDevelpoer.repository;

import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.Post;
import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDataRepository extends JpaRepository<Post,String> {
    Page<Post> findByUser(UserAccount userAccount, Pageable pageable);

}
