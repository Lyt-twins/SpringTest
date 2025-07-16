package com.aloha.SpringTest.service;

import java.util.List;

import com.aloha.SpringTest.domain.Page;
import com.aloha.SpringTest.domain.Posts;
import com.github.pagehelper.PageInfo;

public interface PostService {

    // 목록
    public List<Posts> list() throws Exception;
    // 페이징 목록
    public List<Posts> page(Page pagination) throws Exception;
    // ⭐ pagehelper 를 이용한 페이징 목록
    public PageInfo<Posts> page(int page, int size) throws Exception;
    // 조회
    public Posts select(Long no) throws Exception;
    // 등록
    public boolean insert(Posts post) throws Exception;
    // 수정
    public boolean update(Posts post) throws Exception;
    // 삭제
    public boolean delete(Long no) throws Exception;
    
}
