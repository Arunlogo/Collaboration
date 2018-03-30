package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface Frienddao {
List<User>suggestedusers(String email);

void addfriend(Friend friend);
public List<Friend> getpendingreq(String email);

void updatereq(Friend friend);

List<User> getallfriends(String email);
}
