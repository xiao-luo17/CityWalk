package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: Richerlv
 * @date: 2022/10/7 10:15
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Activity {

    private int activityId;
    private String activityTitle;
    private String hostOrganization;
    private Date publishDate;
    private String activityDetail;
    private int likes;
}
