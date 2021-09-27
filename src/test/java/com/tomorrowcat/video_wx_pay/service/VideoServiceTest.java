package com.tomorrowcat.video_wx_pay.service;

import com.tomorrowcat.video_wx_pay.domain.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void findAll() {
        List<Video> list = videoService.findAll();
        assertNotNull(list);
        for(Video video : list){
            System.out.println(video.getTitle());
        }
    }

    @Test
    public void findById() {
        Video video = videoService.findById(1);
        assertNotNull(video);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }
}