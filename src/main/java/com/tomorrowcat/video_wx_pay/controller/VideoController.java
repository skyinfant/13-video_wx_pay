package com.tomorrowcat.video_wx_pay.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tomorrowcat.video_wx_pay.domain.Video;
import com.tomorrowcat.video_wx_pay.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: kim
 * @create: 2021-06-27 21:21
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/api/v1/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    /**
     * @description: 分页接口，获取某一页的视频数据
     * @param:
     * @return: Object
     */
    @GetMapping("page")
    public Object pageVideo(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "10") int seze) {

        PageHelper.startPage(page, seze);

        List<Video> list = videoService.findAll();

        PageInfo<Video> pageInfo = new PageInfo<>(list);

        Map<String,Object> data = new HashMap<>();
        data.put("total_size", pageInfo.getTotal());//总条数
        data.put("total_page", pageInfo.getPages());//总页数
        data.put("current_page", page);//当前页
        data.put("data", pageInfo.getList());//当前页的数据

        return data;

    }

    /**
     * @description: 视频详情
     * @param: vidoeId
     * @return: Object
     */
    @GetMapping("find_by_id")
    public Object findById(@RequestParam(value = "video_id",required = true) int videoId) {
        return videoService.findById(videoId);
    }


}