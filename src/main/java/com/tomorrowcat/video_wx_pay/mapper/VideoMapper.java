package com.tomorrowcat.video_wx_pay.mapper;

import com.tomorrowcat.video_wx_pay.domain.Video;
import com.tomorrowcat.video_wx_pay.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author: Kim
 * @create: 2021-06-27 18:30
 */
public interface VideoMapper {

    @Select("select * from video")
    List<Video> findAll();

    @Select("select * from video where id = #{id}")
    Video findById(int id);

//    @Update("update video set title = #{title} where id = #{id}")
    @UpdateProvider(type = VideoProvider.class,method = "updateVideo")
    int upate(Video video);

    @Delete("DELETE FROM video WHERE id =#{id}")
    int delete(int id);


    @Insert("INSERT INTO `video` ( `title`, `summary`, `cover_img`, " +
            "`view_num`, `price`, `create_time`, `online`, `point`) " +
            "VALUES (#{title},#{summary},#{coverImg},#{viewNum}," +
            "#{price},#{createTime},#{online},#{point});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(Video video);
}