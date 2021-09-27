package com.tomorrowcat.video_wx_pay.service.impl;

import com.tomorrowcat.video_wx_pay.domain.Video;
import com.tomorrowcat.video_wx_pay.mapper.VideoMapper;
import com.tomorrowcat.video_wx_pay.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: kim
 * @create: 2021-06-27 21:16
 * @version: 1.0.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.upate(video);
    }

    @Override
    public int delete(int id) {
        return videoMapper.delete(id);
    }

    @Override
    public int save(Video video) {
        int rows = videoMapper.save(video);
        System.out.println("保存对象的id= " + video.getId());

        return rows;
    }
}