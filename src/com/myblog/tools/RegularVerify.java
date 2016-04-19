package com.myblog.tools;

public class RegularVerify {
	public final static boolean verifyOnlyLetterAndNumber(String str) {
		return str.matches("^[0-9A-Za-z][0-9A-Za-z_]*");
	}
}
