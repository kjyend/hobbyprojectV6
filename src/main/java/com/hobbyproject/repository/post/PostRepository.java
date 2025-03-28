package com.hobbyproject.repository.post;

import com.hobbyproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>,PostRepositoryCustom {
    @Query("SELECT e.count FROM Post e WHERE e.postId = :id")
    Long findCountByPostId(@Param("id") Long id);

    @Query("SELECT p.likeCount FROM Post p WHERE p.postId = :id")
    Long findLikeCountByPostId(@Param("id") Long postId);

    @Query("SELECT p.member.loginId FROM Post p WHERE p.postId = :id")
    String findPostOwnerLoginId(@Param("id") Long postId);
}
