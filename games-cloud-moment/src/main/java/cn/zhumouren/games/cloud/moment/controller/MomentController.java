package cn.zhumouren.games.cloud.moment.controller;


import cn.zhumouren.games.cloud.moment.constant.PageConstants;
import cn.zhumouren.games.cloud.moment.entity.Moment;
import cn.zhumouren.games.cloud.moment.service.IMomentService;
import cn.zhumouren.games.cloud.moment.utils.JwtUtils;
import cn.zhumouren.games.cloud.moment.vo.MomentVO;
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
public class MomentController {

    @Autowired
    private IMomentService momentService;

    @PostMapping("/moment")
    public boolean postContent(@RequestBody Moment moment, @RequestHeader("access_token") String accessToken) {
        moment.setUsername(JwtUtils.getString(accessToken, "user_name"));
        moment.setCreated(LocalDateTime.now());
        return momentService.postMoment(moment);
    }

    @DeleteMapping("/moment")
    public boolean deleteContent(@RequestBody Moment moment, @RequestHeader("access_token") String accessToken) {
        return momentService.deleteMoment(moment, accessToken);
    }

    @GetMapping("/moment/link/{momentId}")
    public IPage<Moment> getContentLink(@PathVariable("username") String username,
                                        @PathVariable("momentId") Long momentId,
                                        @RequestParam("current") Integer current,
                                        @RequestParam("size") Integer size) {
        Page<Moment> page = new Page<>();
        PageConstants.constantPageConfig(page, current, size);
        return momentService.getMomentLinkPage(page, momentId);
    }

    @GetMapping("/{username}/status/{momentId}")
    public IPage<MomentVO> getContentVOLink(@PathVariable("username") String username,
                                            @PathVariable("momentId") Long momentId,
                                            @RequestParam("current") Integer current,
                                            @RequestParam("size") Integer size) {
        Page<Moment> page = new Page<>();
        PageConstants.constantPageConfig(page, current, size);
        return momentService.getMomentVOLinkPage(page, momentId);
    }

    @GetMapping("/moment/{momentId}")
    public Moment getContent(@PathVariable("momentId") Long momentId) {
        return momentService.getById(momentId);
    }

    @GetMapping("/{momentId}/quote")
    public IPage<String> getContentQuoteUsernamePage(@PathVariable("momentId") Long momentId,
                                                     @RequestParam("current") Integer current,
                                                     @RequestParam("size") Integer size) {
        Page<String> page = new Page<>();
        PageConstants.constantPageConfig(page, current, size);
        return momentService.getMomentQuoteUsernamePage(page, momentId);
    }
}
