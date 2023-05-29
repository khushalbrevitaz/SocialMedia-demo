package com.socialMediaDevelpoer.socialMediaDevelpoer.repository;

import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.LikeDislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDislikeRepository extends JpaRepository<LikeDislike,String> {
    boolean existsByUseridAndPostidAndPostuserid(String userId,String postId ,String postUserId);
    LikeDislike findByUseridAndPostidAndPostuserid(String userId,String postId ,String postUserId);


    //void delete(String id, String postId, String postuserId);
}
