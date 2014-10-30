package org.md.deidentifier;

public class Key {
	public int index;
	public String originalValue;
	public String newValue;
	
	public Key(int index, String orig, String newValue)
	{
		this.index = index;
		this.originalValue = orig;
		this.newValue = newValue;
	}

}
