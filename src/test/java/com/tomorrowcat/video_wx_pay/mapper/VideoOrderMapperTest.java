package com.tomorrowcat.video_wx_pay.mapper;

import com.tomorrowcat.video_wx_pay.domain.Video;
import com.tomorrowcat.video_wx_pay.domain.VideoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoOrderMapperTest {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Test
    public void insert() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setVideoTitle("C++从入门到精通");
        videoOrder.setDel(0);
        videoOrder.setHeadImg("ajflasd");
        videoOrder.setOutTradeNo("888");
        videoOrderMapper.insert(videoOrder);
        assertNotNull(videoOrder.getId());
    }

    @Test
    public void findById() {
        VideoOrder videoOrder = videoOrderMapper.findById(3);
        assertNotNull(videoOrder);
    }

    @Test
    public void findOutTradeNo() {
    }

    @Test
    public void del() {
    }

    @Test
    public void findMyOrderList() {
    }

    @Test
    public void updateVideoOrderByOutTradeNo() {
    }
}