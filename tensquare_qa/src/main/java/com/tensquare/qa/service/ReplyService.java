package com.tensquare.qa.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.qa.mapper.ReplyMapper;
import com.tensquare.qa.pojo.Reply;
import com.tensquare.qa.pojo.ReplyExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    IdWorker idWorker;

    public List<Reply> findAll() {
        ReplyExample example = new ReplyExample();
        return replyMapper.selectByExample(example);
    }

    public void save(Reply reply) {
        reply.setId(idWorker.nextId()+"");
        replyMapper.insert(reply);
    }

    public void update(Reply reply) {
        replyMapper.updateByPrimaryKeySelective(reply);
    }

    public void delete(String replyId) {
        replyMapper.deleteByPrimaryKey(replyId);
    }

    public Reply findById(String replyId) {
        return replyMapper.selectByPrimaryKey(replyId);
    }

    public List<Reply> search(Reply reply) {
        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(reply.getId())) {
               criteria.andIdEqualTo(reply.getId());
            }
          	if (StringUtils.isNotBlank(reply.getProblemid())) {
               criteria.andIdEqualTo(reply.getProblemid());
            }
          	if (StringUtils.isNotBlank(reply.getContent())) {
               criteria.andIdEqualTo(reply.getContent());
            }
          	if (StringUtils.isNotBlank(reply.getUserid())) {
               criteria.andIdEqualTo(reply.getUserid());
            }
          	if (StringUtils.isNotBlank(reply.getNickname())) {
               criteria.andIdEqualTo(reply.getNickname());
            }

        return replyMapper.selectByExample(example);
    }

    public PageResult<Reply> searchByPage(Reply reply, int page, int size) {
        PageHelper.startPage(page-1,size);
        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(reply.getId())) {
               criteria.andIdEqualTo(reply.getId());
            }
          	if (StringUtils.isNotBlank(reply.getProblemid())) {
               criteria.andIdEqualTo(reply.getProblemid());
            }
          	if (StringUtils.isNotBlank(reply.getContent())) {
               criteria.andIdEqualTo(reply.getContent());
            }
          	if (StringUtils.isNotBlank(reply.getUserid())) {
               criteria.andIdEqualTo(reply.getUserid());
            }
          	if (StringUtils.isNotBlank(reply.getNickname())) {
               criteria.andIdEqualTo(reply.getNickname());
            }

        List<Reply> replyList = replyMapper.selectByExample(example);
        PageInfo<Reply> pageInfo = new PageInfo<>(replyList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

}
