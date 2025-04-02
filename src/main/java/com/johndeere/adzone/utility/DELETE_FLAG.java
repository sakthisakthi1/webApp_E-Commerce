package com.johndeere.adzone.utility;

public enum DELETE_FLAG {
	FALSE(0), TRUE(1);

	public int value;  
	private DELETE_FLAG(int value){ 
		this.value=value; 
	}

}