package com.ef.gateways.impl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BlockedComments {

	@Id
	@GeneratedValue
	public Long id;
	
	public String comment;
	
	public BlockedComments() {}
	
	public BlockedComments(String comment) {
		this.comment = comment;
	}
	
	
	
}
