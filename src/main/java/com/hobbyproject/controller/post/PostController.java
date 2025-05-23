package com.hobbyproject.controller.post;

import com.hobbyproject.dto.post.request.SearchDto;
import com.hobbyproject.dto.post.response.PostPagingResponse;
import com.hobbyproject.dto.post.response.PostResponseDto;
import com.hobbyproject.entity.CustomUserDetails;
import com.hobbyproject.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public String postList(@RequestParam(name = "page",defaultValue = "1") int page,@AuthenticationPrincipal UserDetails userDetails, Model model) {
        SearchDto postSearch = new SearchDto(page, 10);
        PostPagingResponse response = postService.getList(postSearch);

        model.addAttribute("posts", response.getPosts());
        model.addAttribute("totalPostCount", response.getTotalPostCount());
        model.addAttribute("currentPage", page);

        boolean isLoggedIn = userDetails != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("userId", isLoggedIn ? getUserId(userDetails) : null);

        return "post/postlist";
    }

    private String getUserId(UserDetails userDetails) {
        if (userDetails instanceof CustomUserDetails) {
            return ((CustomUserDetails) userDetails).getUsername();
        }
        return null;
    }

    @GetMapping("/post/{postId}")
    public String postView(@PathVariable("postId") Long postId,@AuthenticationPrincipal UserDetails userDetails, Model model){
        PostResponseDto post = postService.findPost(postId);

        model.addAttribute("post", post);
        return "post/postview";
    }

    @GetMapping("/post/edit/{postId}")
    public String postEdit(@PathVariable("postId") Long postId, Model model){
        model.addAttribute("postId", postId);
        return "post/postedit";
    }

    @GetMapping("/post/write")
    public String postWrite(){
        return "/post/postwrite";
    }
}
