package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Richerlv
 * @date: 2022/9/19 20:11
 * @description: 创作内容对应的图片，之所以单独建一个表，是考虑到一个文章对应多张图的情况
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreationPhoto {
    private int photoId;
    private String photoUrl;
    private int creationId;
}
