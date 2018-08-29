package com.x.jwof.domain.data.response;

import com.x.jwof.domain.data.entity.APIError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private boolean success;
    private Object data;
    private APIError error;
}
