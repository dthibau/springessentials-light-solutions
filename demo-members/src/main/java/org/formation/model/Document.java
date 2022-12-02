package org.formation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.formation.controller.views.MemberViews;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Entity
@Data
public class Document implements Serializable {

	private static final long serialVersionUID = 6590486482810196501L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(MemberViews.Detail.class)
	private long id;
	
	@JsonView(MemberViews.Detail.class)
	private String name,contentType;
	
	@Lob
	private byte[] data;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(MemberViews.Detail.class)
	private Date uploadedDate;

	
	
	
}
