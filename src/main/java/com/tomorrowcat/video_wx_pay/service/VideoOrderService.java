package com.tomorrowcat.video_wx_pay.service;

import com.tomorrowcat.video_wx_pay.domain.VideoOrder;
import com.tomorrowcat.video_wx_pay.dto.VideoOrderDto;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Kim
 * @create: 2021-07-02 10:44
 */
@Service
public interface VideoOrderService {

    /**
     * @description:  下单接口
     * @param: videoOrder
     * @return: VideoOrder
     */
    String save(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 根据流水号查找订单
     * @param outTradeNo
     * @return
     */
    VideoOrder findByOutTradeNo(String outTradeNo);

    /**
     * 根据流水号更新订单
     * @param videoOrder
     * @return
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);
}