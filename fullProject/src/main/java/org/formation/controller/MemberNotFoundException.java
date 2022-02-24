package org.formation.controller;

public class MemberNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberNotFoundException() {
		super();
	}
	
	public MemberNotFoundException(String msg) {
		super(msg);
	}
}
