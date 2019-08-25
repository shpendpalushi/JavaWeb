package com.shpend.app.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Vote {
	private String upvote;
	private VoteId pk;
	public String getUpvote() {
		return upvote;
	}
	public void setUpvote(String upvote) {
		this.upvote = upvote;
	}
	
	@EmbeddedId
	public VoteId getPk() {
		return pk;
	}
	public void setPk(VoteId pk) {
		this.pk = pk;
	}
	

}
