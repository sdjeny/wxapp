package org.sdjen.apps.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InitDate {
	@Id
	public Date date;

	public String json;
}