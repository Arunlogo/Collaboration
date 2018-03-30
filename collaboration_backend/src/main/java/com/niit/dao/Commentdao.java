package com.niit.dao;

import java.util.List;

import com.niit.model.comments;

public interface Commentdao {
public List<comments>getallcoments(int id);
public void addcomment(comments comment);
}
