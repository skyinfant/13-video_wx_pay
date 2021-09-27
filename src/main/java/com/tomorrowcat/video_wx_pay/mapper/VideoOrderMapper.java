package com.tomorrowcat.video_wx_pay.mapper;

import com.tomorrowcat.video_wx_pay.domain.VideoOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description: 订单dao层
 * @author: kim
 * @create: 2021-07-01 18:59
 * @version: 1.0.0
 */
public interface VideoOrderMapper {

    /**
     * 保存订单，返回包含主键
     *
     * @param videoOrder
     * @return
     */
    @Insert("INSERT INTO `video_order` (`openid`, `out_trade_no`, `state`, `create_time`," +
            " `notify_time`, `total_fee`, `nickname`, `head_img`, `video_id`, `video_title`," +
            " `video_img`, `user_id`, `ip`, `del`)" +
            "VALUES" +
            "(#{openid},#{outTradeNo},#{state},#{createTime},#{notifyTime},#{totalFee}," +
            "#{nickname},#{headImg},#{videoId},#{videoTitle},#{videoImg},#{userId},#{ip},#{del});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(VideoOrder videoOrder);

    /**
     * 根据主键查找订单
     * @param id
     * @return
     */
    @Select("select * from video_order where id=#{id} and del=0")
    VideoOrder findById(int id);

    /**
     * 根据订单号查找订单
     * @param outTradeNo
     * @return
     */
    @Select("select * from video_order where out_trade_no=#{outTradeNo} and del=0")
    VideoOrder findByOutTradeNo(String outTradeNo);

    /**
     * 逻辑删除订单
     * @param id
     * @param userId
     * @return
     */
    @Update("update video_Order set del=1 where id = #{id} and user_id = #{user_id}")
    int del(@Param("id") int id, @Param("user_id") int userId);



    /**
     * 查找我的全部订单
     * @param userId
     * @return
     */
    @Select("select * from video_order where user_id=#{userId}")
    List<VideoOrder> findMyOrderList(int userId);


    /**
     * 根据订单流水号更新
     * @param videoOrder
     * @return
     */
    @Update("update video_order set state=#{state} ,notify_time=#{notifyTime},openid=#{openid} " +
            "where out_trade_no=#{outTradeNo} and state=0 and del=0 ")
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);


















}