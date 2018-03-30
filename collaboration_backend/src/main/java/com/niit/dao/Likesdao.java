package com.niit.dao;

import com.niit.model.Blogs;
import com.niit.model.Likes;

public interface Likesdao {
public Likes hasuserLiked(int postId,String email);
public Blogs updateLikes(int postId,String email);
}
