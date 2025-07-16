package com.aloha.SpringTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.SpringTest.domain.Page;
import com.aloha.SpringTest.domain.Posts;
import com.aloha.SpringTest.mapper.PostMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PostServiceImpl implements PostService {

    @Autowired 
    private PostMapper postMapper;

    @Override
    public List<Posts> list() throws Exception {
        return postMapper.list();
    }

    @Override
    public Posts select(Long no) throws Exception {
        return postMapper.select(no);
    }

    @Override
    public boolean insert(Posts post) throws Exception {
        return postMapper.insert(post) > 0;
    }
    
    @Override
    public boolean update(Posts post) throws Exception {
        return postMapper.update(post) > 0;
    }
    
    @Override
    public boolean delete(Long no) throws Exception {
        return postMapper.delete(no) > 0;
    }

    @Override
    public List<Posts> page(Page pagination) throws Exception {
        // 데이터 수 조회
        long total = postMapper.count();
        pagination.setTotal(total);
        return postMapper.page(pagination);
    }

    // ⭐ PageHelper 기반 페이징 목록 조회
    @Override
    public PageInfo<Posts> page(int page, int size) throws Exception {
        PageHelper.startPage(page, size);
        List<Posts> list = postMapper.list();
        return new PageInfo<>(list, 10); // 페이지 네비게이션 개수 10
    }
    
}
