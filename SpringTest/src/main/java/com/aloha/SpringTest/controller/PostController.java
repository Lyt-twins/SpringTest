package com.aloha.SpringTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.aloha.SpringTest.domain.Page;
import com.aloha.SpringTest.domain.Posts;
import com.aloha.SpringTest.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired private PostService postService;

    // ✅ 게시글 목록
    @GetMapping("/list")
    public String list(Model model, @ModelAttribute Page pagination) throws Exception {
        int page = (int) pagination.getPage();
        int size = 5; // ✅ 고정값 설정 (5개씩 보기)

        // PageHelper로 페이징 처리
        PageHelper.startPage(page, size);
        List<Posts> list = postService.list();
        PageInfo<Posts> pageInfo = new PageInfo<>(list, 10); // 10: 페이지 번호 최대 노출

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pagination", pagination);

        // ✅ pageUri에서 size 파라미터 제거
        String pageUri = "/posts/list";
        model.addAttribute("pageUri", pageUri);

        return "posts/list";
    }
    
     // ✅ 글쓰기 폼 (create.html)
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("post", new Posts());
        return "posts/create";
    }

    // ✅ 글 등록 처리
    @PostMapping("/create")
    public String create(@ModelAttribute Posts post) throws Exception {
        boolean result = postService.insert(post);
        return result ? "redirect:/posts/list" : "posts/create";
    }

    // ✅ 상세 조회 (read.html)
    @GetMapping("/read/{no}")
    public String read(@PathVariable Long no, Model model) throws Exception {
        Posts post = postService.select(no);
        model.addAttribute("post", post);
        return "posts/read";
    }

    // ✅ 수정 폼 (update.html)
    @GetMapping("/update/{no}")
    public String updateForm(@PathVariable Long no, Model model) throws Exception {
        Posts post = postService.select(no);
        model.addAttribute("post", post);
        return "posts/update";
    }

    // ✅ 수정 처리
    @PostMapping("/update/{no}")
    public String update(@PathVariable Long no, @ModelAttribute Posts post) throws Exception {
        post.setNo(no);
        boolean result = postService.update(post);
        return result ? "redirect:/posts/list" : "posts/update";
    }

    // ✅ 삭제 처리
    @PostMapping("/delete/{no}")
    public String delete(@PathVariable Long no) throws Exception {
        postService.delete(no);
        return "redirect:/posts/list";
    }
}
