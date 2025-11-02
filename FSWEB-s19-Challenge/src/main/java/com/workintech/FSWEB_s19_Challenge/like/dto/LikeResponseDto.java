package com.workintech.FSWEB_s19_Challenge.like.dto;

import java.time.ZonedDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDto {
    private Long id;
    private Long userId;
    private Long targetId; 
    private String targetType;
    private ZonedDateTime createdAt;
}
