package com.example.swagger.nail.dto;

import lombok.Data;

@Data
public class NailGroupRequest {
    private Integer nailThumbId; // Integer로 변경
    private Integer nailIndexId;
    private Integer nailMiddleId;
    private Integer nailRingId;
    private Integer nailPinkyId;
}

