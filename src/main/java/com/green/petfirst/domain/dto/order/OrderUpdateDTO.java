package com.green.petfirst.domain.dto.order;

import com.green.petfirst.domain.entity.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderUpdateDTO {
    private long orderNo;
    private Status status;
}
