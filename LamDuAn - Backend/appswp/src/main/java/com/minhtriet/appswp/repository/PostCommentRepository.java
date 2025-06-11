package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.PostComment;
import com.minhtriet.appswp.entity.BlogPost;
import com.minhtriet.appswp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findByPost(BlogPost post);
    List<PostComment> findByUser(User user);
    List<PostComment> findByParentComment(PostComment parent);
}
