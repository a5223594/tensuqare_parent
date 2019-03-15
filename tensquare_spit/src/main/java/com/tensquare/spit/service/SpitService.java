package com.tensquare.spit.service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.spit.Spit;
import com.tensquare.spit.dao.SpitDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpitService  {

    @Autowired
    SpitDao spitDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    IdWorker idWorker;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    public void add(Spit spit) {
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setThumbup(0);
        spit.setVisits(0);
        spit.setShare(0);
        spit.setComment(0);
        spit.setState("1");
        if(StringUtils.isNotBlank(spit.getParentid())){
            Query query = new Query().addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update().inc("comment",1);
            mongoTemplate.updateFirst(query,update,Spit.class);
        }
        spitDao.save(spit);
    }

    public void update(Spit spit){
        Query query = new Query(Criteria.where("_id").is(spit.get_id()));
        Update update = new Update();
        if(StringUtils.isNotBlank(spit.getContent())){
             update.set("content",spit.getContent());
        }
        if(StringUtils.isNotBlank(spit.getNickname())){
             update.set("nickname",spit.getNickname());
        }
        if(StringUtils.isNotBlank(spit.getParentid())){
             update.set("parentid",spit.getParentid());
        }
        if(StringUtils.isNotBlank(spit.getState())){
             update.set("state",spit.getState());
        }
        if(StringUtils.isNotBlank(spit.getUserid())){
             update.set("userid",spit.getUserid());
        }
        if(spit.getComment()!=null){
            update.set("comment",spit.getComment());
        }
        if (spit.getVisits() != null) {
            update.set("visits", spit.getVisits());
        }
        mongoTemplate.updateFirst(query,update,Spit.class);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentId(String parentid, int page, int size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentid, request);
    }

    public void updateThumbup(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        Update update = new Update().inc("thumbup",1);
        mongoTemplate.updateFirst(query, update, Spit.class);
    }


}
