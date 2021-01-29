package cn.zhumouren.games.cloud.moment.controller;


import cn.zhumouren.games.cloud.moment.entity.Moment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@RestController
@RequestMapping
public class  LikesController {

    @PostMapping("/likes")
    public boolean postLike(@RequestParam("contentId") Long contentId,
                            @RequestHeader("access_token") String accessToken){

        return false;
    }

    public IPage<Moment> getUserLikes(){

        return null;
    }

//    public IPage<>

}
