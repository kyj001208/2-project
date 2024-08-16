package com.green.petfirst.domain.dto;

import com.green.petfirst.domain.entity.RequestStatus;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private Long requestNo;
    private RequestStatus requestStatus;
    private String requestType;
}

