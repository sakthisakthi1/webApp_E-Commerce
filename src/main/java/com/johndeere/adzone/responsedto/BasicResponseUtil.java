package com.johndeere.adzone.responsedto;

public class BasicResponseUtil {

	public static <T> BasicResponse<T> setBasicResponse(String successMsg, String successCode, String errorCode, String errorMsg, T jdUserResponseObj){
		BasicResponse<T> basicResponse = new BasicResponse<>();
		SuccessResponse successResponse = new SuccessResponse(successCode, successMsg);
		ErrorResponse errorResponse = new ErrorResponse(errorCode, errorMsg, null);
		basicResponse.setError(errorResponse);
		basicResponse.setMessage(successResponse);
		basicResponse.setData(jdUserResponseObj);
		return basicResponse;
    }
}
