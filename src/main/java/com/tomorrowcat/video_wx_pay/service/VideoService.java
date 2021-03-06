package com.tomorrowcat.video_wx_pay.service;

import com.tomorrowcat.video_wx_pay.domain.Video;
import java.util.List;

/**
 * 视频业务类接口
 */
public interface VideoService {

    List<Video> findAll();

    Video findById(int id);

    int update(Video video);

    int delete(int id);

    int save(Video video);

}
