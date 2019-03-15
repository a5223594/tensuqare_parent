package com.tensquare.article.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.article.mapper.ChannelMapper;
import com.tensquare.article.pojo.Channel;
import com.tensquare.article.pojo.ChannelExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    IdWorker idWorker;

    public List<Channel> findAll() {
        ChannelExample example = new ChannelExample();
        return channelMapper.selectByExample(example);
    }

    public void save(Channel channel) {
        channel.setId(idWorker.nextId()+"");
        channelMapper.insert(channel);
    }

    public void update(Channel channel) {
        channelMapper.updateByPrimaryKeySelective(channel);
    }

    public void delete(String channelId) {
        channelMapper.deleteByPrimaryKey(channelId);
    }

    public Channel findById(String channelId) {
        return channelMapper.selectByPrimaryKey(channelId);
    }

    public List<Channel> search(Channel channel) {
        ChannelExample example = new ChannelExample();
        ChannelExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(channel.getId())) {
               criteria.andIdEqualTo(channel.getId());
            }
          	if (StringUtils.isNotBlank(channel.getName())) {
               criteria.andIdEqualTo(channel.getName());
            }
          	if (StringUtils.isNotBlank(channel.getState())) {
               criteria.andIdEqualTo(channel.getState());
            }

        return channelMapper.selectByExample(example);
    }

    public PageResult<Channel> searchByPage(Channel channel, int page, int size) {
        PageHelper.startPage(page-1,size);
        ChannelExample example = new ChannelExample();
        ChannelExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(channel.getId())) {
               criteria.andIdEqualTo(channel.getId());
            }
          	if (StringUtils.isNotBlank(channel.getName())) {
               criteria.andIdEqualTo(channel.getName());
            }
          	if (StringUtils.isNotBlank(channel.getState())) {
               criteria.andIdEqualTo(channel.getState());
            }

        List<Channel> channelList = channelMapper.selectByExample(example);
        PageInfo<Channel> pageInfo = new PageInfo<>(channelList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
