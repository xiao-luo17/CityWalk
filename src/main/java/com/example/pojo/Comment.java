package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Richerlv
 * @date: 2022/10/8 19:32
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Comment {
    private int commentId;
    private int activityId;
    private String commentContent;
    private String commentDate;
    private String openId;
}
