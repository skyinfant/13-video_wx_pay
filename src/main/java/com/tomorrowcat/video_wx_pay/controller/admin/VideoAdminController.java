package com.tomorrowcat.video_wx_pay.controller.admin;

import com.tomorrowcat.video_wx_pay.domain.Video;
import com.tomorrowcat.video_wx_pay.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 管理员操作api
 * @author: kim
 * @create: 2021-06-27 22:21
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/admin/api/v1/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    /**
     * @description: 删除视频
     * @param: videoId
     * @return: Object
     */
    @DeleteMapping("del_by_id")
    public Object delById(@RequestParam(value = "video_id",required = true)
                                      int videoId) {
        return videoService.delete(videoId);
    }

    /**
     * @description: 更新视频
     * @param: videoId
     * @param: title
     * @return: Object
     */
    @PutMapping("update_by_id")
    public Object update(@RequestBody Video video) {
        return videoService.update(video);

    }

    /**
     * @description: 保存视频
     * @param: title
     * @return: Object
     */
    @PostMapping("save")
    public Object save(@RequestBody Video video) {

        return videoService.save(video);
    }
}