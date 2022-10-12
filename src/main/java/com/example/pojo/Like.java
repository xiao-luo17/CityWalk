package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Richerlv
 * @date: 2022/10/7 14:03
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Like {
    private int likeId;
    private String openId;
    private int activityId;
}
