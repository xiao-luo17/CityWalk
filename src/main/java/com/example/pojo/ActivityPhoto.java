package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:18
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ActivityPhoto {
    private int activityPhotoId;
    private String activityPhotoUrl;
    private int activityId;
}
