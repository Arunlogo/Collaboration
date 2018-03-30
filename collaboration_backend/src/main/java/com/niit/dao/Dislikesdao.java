package com.niit.dao;

import com.niit.model.Blogs;
import com.niit.model.Dislikes;

public interface Dislikesdao {
public Dislikes hasuserdisliked(int id,String email);
public Blogs updatedislikes(int id,String email);
}
