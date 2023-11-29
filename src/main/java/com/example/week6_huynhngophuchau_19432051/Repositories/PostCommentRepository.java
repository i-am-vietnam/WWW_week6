package com.example.week6_huynhngophuchau_19432051.Repositories;

import com.example.week6_huynhngophuchau_19432051.Model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findPostCommentByPost_IdAndParentComment_Id(Long postId, Long parrentCommentId);
    List<PostComment> findByPost_IdAndParentComment_Id(Long postId, Long parrentPostCommentId);

}
