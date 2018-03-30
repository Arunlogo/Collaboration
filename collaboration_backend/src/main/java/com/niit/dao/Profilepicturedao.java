package com.niit.dao;

import com.niit.model.Profilepic;

public interface Profilepicturedao {
public void uploadpic(Profilepic profilepic);
public Profilepic getpic(String email);
}
