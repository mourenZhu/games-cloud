package cn.zhumouren.games.cloud.content.service.impl;

import cn.zhumouren.games.cloud.content.entity.Content;
import cn.zhumouren.games.cloud.content.entity.Likes;
import cn.zhumouren.games.cloud.content.mapper.LikesMapper;
import cn.zhumouren.games.cloud.content.service.IContentService;
import cn.zhumouren.games.cloud.content.service.ILikesService;
import cn.zhumouren.games.cloud.content.vo.ContentVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhumouren
 * @since 2021-01-19
 */
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements ILikesService {

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private IContentService contentService;

    @Override
    public Map<Long, Integer> getContentLikeNumsMap(List<Long> contentIdList) {
        return likesMapper.getContentLikeNumsMap(contentIdList);
    }

    @Override
    public IPage<String> getContentLikeUsernames(Page<String> page, Long contentId) {
        return likesMapper.getContentLikeUsernames(page, contentId);
    }

    @Override
    public IPage<ContentVO> getUserLikeContentVOPage(Page<Content> page, String username) {
        Page<Long> contentIdPage = new Page<>();
        contentIdPage.setSize(page.getSize());
        contentIdPage.setCurrent(page.getCurrent());
        contentIdPage.setTotal(page.getTotal());
        contentIdPage.setPages(page.getPages());

        IPage<Long> userLikeContentIdPage = likesMapper.getUserLikeContentIdPage(contentIdPage, username);
        List<Long> contentIdList = userLikeContentIdPage.getRecords();

        IPage<ContentVO> contentVOIPage = new Page<>();
        contentVOIPage.setRecords(contentService.getContentVOList(contentIdList));
        contentVOIPage.setPages(userLikeContentIdPage.getPages());
        contentVOIPage.setSize(userLikeContentIdPage.getSize());
        contentVOIPage.setTotal(userLikeContentIdPage.getTotal());
        contentVOIPage.setCurrent(userLikeContentIdPage.getCurrent());
        return contentVOIPage;
    }
}
