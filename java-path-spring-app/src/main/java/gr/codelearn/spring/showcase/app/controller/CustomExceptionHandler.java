package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.transfer.ApiError;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionHandler extends BaseComponent {
	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<ApiResponse<?>> handleNonExistence(NoSuchElementException exception, WebRequest webRequest) {
		logger.error("Reference to non-existent object", exception);
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(exception, HttpStatus.NOT_FOUND, webRequest, "Reference to non-existent object")).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
																	   WebRequest webRequest) {
		logger.error("Method argument is not valid", exception);
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(exception, HttpStatus.BAD_REQUEST, webRequest)).build(),
				HttpStatus.BAD_REQUEST);
	}

	private ApiError getApiError(Exception exception, HttpStatus httpStatus, WebRequest webRequest) {
		String path = webRequest.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(httpStatus.value(),exception.getMessage(),path);
	}

	private ApiError getApiError(Exception exception, HttpStatus httpStatus, WebRequest webRequest,
								 String customMessage) {
		String path = webRequest.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path,"uri=","");
		}
		return new ApiError(httpStatus.value(),customMessage!= null ? customMessage : exception.getMessage(),path);
	}

}
