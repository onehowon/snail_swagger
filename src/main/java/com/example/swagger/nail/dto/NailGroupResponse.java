package com.example.swagger.nail.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NailGroupResponse {
    private String id;
    private NailResponse nailThumb;
    private NailResponse nailIndex;
    private NailResponse nailMiddle;
    private NailResponse nailRing;
    private NailResponse nailPinky;
    private LocalDateTime createdAt; // LocalDateTime으로 변경
}