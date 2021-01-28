package cn.zhumouren.games.cloud.content.controller;


import cn.zhumouren.games.cloud.content.entity.Content;
import cn.zhumouren.games.cloud.content.service.IContentService;
import cn.zhumouren.games.cloud.content.utils.JwtUtils;
import cn.zhumouren.games.cloud.content.vo.ContentVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@RestController
@RequestMapping
public class ContentController {

    @Autowired
    private IContentService contentService;

    @PostMapping("/content")
    public boolean postContent(@RequestBody Content content, @RequestHeader("access_token") String accessToken) {
        content.setUsername(JwtUtils.getString(accessToken, "user_name"));
        content.setCreated(LocalDateTime.now());
        return contentService.postContent(content);
    }

    @DeleteMapping("/content")
    public boolean deleteContent(@RequestBody Content content, @RequestHeader("access_token") String accessToken) {
        return contentService.deleteContent(content, accessToken);
    }

    @GetMapping("/content/link/{contentId}")
    public IPage<Content> getContentLink(@PathVariable("username") String username,
                                         @PathVariable("contentId") Long contentId,
                                         @RequestParam("current") Integer current,
                                         @RequestParam("size") Integer size) {
        Page<Content> page;
        if (current == null || size == null) {
            page = new Page(1, 10);
        } else {
            page = new Page<>(current, size);
        }

        return contentService.getContentLink(page, contentId);
    }

    @GetMapping("/{username}/status/{contentId}")
    public IPage<ContentVO> getContentVOLink(@PathVariable("username") String username,
                                             @PathVariable("contentId") Long contentId,
                                             @RequestParam("current") Integer current,
                                             @RequestParam("size") Integer size) {
        Page<Content> page;
        if (current == null || size == null) {
            page = new Page(1, 10);
        } else {
            page = new Page<>(current, size);
        }
        return contentService.getContentVOLink(page, contentId);
    }

    @GetMapping("/content/{contentId}")
    public Content getContent(@PathVariable("contentId") Long contentId) {
        return contentService.getById(contentId);
    }

    public IPage<String> getContentQuoteUsernamePage(Long contentId){
        return null;
    }
}
