package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Richerlv
 * @date: 2022/9/26 16:53
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Place {
    private int placeId;
    private String placeName;
}
