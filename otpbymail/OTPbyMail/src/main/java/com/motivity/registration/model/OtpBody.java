package com.motivity.registration.model;

public class OtpBody {
	
	private static String otp;
	
	public static void setOtp(String otp) {
		OtpBody.otp =otp;
	}
	
	public static String getOtp() {
		return otp;
	}
}
