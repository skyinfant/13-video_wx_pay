package com.tomorrowcat.video_wx_pay.provider;

/**
 * @description:
 * @author: kim
 * @create: 2021-06-28 12:24
 * @version: 1.0.0
 */

import com.tomorrowcat.video_wx_pay.domain.Video;
import org.apache.ibatis.jdbc.SQL;

/**
 * video构建动态sql语句
 */
public class VideoProvider {

    public String updateVideo(final Video video) {
        return new SQL() {
            {
                this.UPDATE("video");

                //条件写法.
                if (video.getTitle() != null) {
                    SET("title=#{title}");
                }
                if (video.getSummary() != null) {
                    SET("summary=#{summary}");
                }
                if (video.getCoverImg() != null) {
                    SET("cover_img=#{coverImg}");
                }
                if (video.getViewNum() != null) {
                    SET("view_num=#{viewNum}");
                }
                if (video.getPrice() != null) {
                    SET("price=#{price}");
                }
                if (video.getOnline() != null) {
                    SET("online=#{online}");
                }
                if (video.getPoint() != null) {
                    SET("point=#{point}");
                }

                this.WHERE("id=#{id}");
            }
        }.toString();
    }
}

