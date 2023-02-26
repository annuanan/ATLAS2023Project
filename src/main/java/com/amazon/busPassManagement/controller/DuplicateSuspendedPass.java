package com.amazon.busPassManagement.controller;

public class DuplicateSuspendedPass extends Exception{
	public DuplicateSuspendedPass() {
		super("You already have a bus pass on this route which is currently in suspended state!");

	}
}