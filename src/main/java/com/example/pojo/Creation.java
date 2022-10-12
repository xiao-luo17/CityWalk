package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: Richerlv
 * @date: 2022/9/19 17:43
 * @description:创作内容实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Creation {
    private int creationId;
    private String creationTitle;
    private String creationContent;
    private Date publishDate;
    private String publishUserOpenId;
    private int likes;
    private int replies;
    private int pageviews;
}
