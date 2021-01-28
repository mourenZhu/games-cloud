package cn.zhumouren.games.cloud.content.service.impl;

import cn.zhumouren.games.cloud.content.entity.Content;
import cn.zhumouren.games.cloud.content.mapper.ContentMapper;
import cn.zhumouren.games.cloud.content.service.IContentService;
import cn.zhumouren.games.cloud.content.service.ILikesService;
import cn.zhumouren.games.cloud.content.service.ITranspondContentService;
import cn.zhumouren.games.cloud.content.utils.JwtUtils;
import cn.zhumouren.games.cloud.content.utils.ListUtils;
import cn.zhumouren.games.cloud.content.utils.UrlUtils;
import cn.zhumouren.games.cloud.content.vo.ContentVO;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
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
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ILikesService likesService;

    @Autowired
    private ITranspondContentService transpondContentService;

    @Override
    @Transactional
    public boolean postContent(Content content) {
        contentMapper.insert(content);
        content.setUrl(UrlUtils.creatContentUrl(content.getUsername(), content.getId()));

        if (content.getParentId() == null || "".equals(content.getParentId())) {
            content.setParentPaths("[" + content.getId() + "]");
        } else {
            Content parentContent = contentMapper.selectById(content.getParentId());
            content.setParentUsername(parentContent.getUsername());
            content.setParentPaths(
                    parentContent.getParentPaths()
                            .replace("]", "," + content.getId() + "]"));
        }

        return SqlHelper.retBool(contentMapper.updateById(content));
    }

    @Override
    public boolean deleteContent(Content content, String token) {
        UpdateWrapper<Content> updateWrapper = new UpdateWrapper();
        String username = JwtUtils.getString(token, "user_name");
        updateWrapper.eq("id", content.getId()).eq("username", username).set("is_delete", 0);

        return SqlHelper.retBool(contentMapper.update(null, updateWrapper));
    }

    @Override
    public IPage<Content> getContentLink(Page<Content> page, Long contentId) {
        List<Long> parentList = getContentParentPaths(contentId);
        return contentMapper.getContentLink(page, contentId, parentList);
    }

    @Override
    public IPage<ContentVO> getContentVOLink(Page<Content> page, Long contentId) {

        IPage<ContentVO> contentVOPage = new Page<>();

        List<ContentVO> contentVOList = getContentVOLinkList(page, contentId);


        contentVOPage.setRecords(contentVOList);
        contentVOPage.setPages(page.getPages());
        contentVOPage.setCurrent(page.getCurrent());
        contentVOPage.setSize(page.getSize());
        contentVOPage.setTotal(page.getTotal());
        return contentVOPage;
    }

    @Override
    public List<ContentVO> getContentVOList(List<Long> contentIdList) {
        List<ContentVO> contentVOList;
        List<Content> contentList = contentMapper.getContentList(contentIdList);
        contentVOList = getContentVOListByContentList(contentList);
        return contentVOList;
    }

    /**
     * 通过contentList获得contentIdList（其实就是简单的分离一下）
     *
     * @param contentList
     * @return
     */
    private List<Long> getContentIdListByContentList(List<Content> contentList) {
        List<Long> idList = new ArrayList<>();
        for (Content c : contentList) {
            idList.add(c.getId());
        }
        return idList;
    }

    /**
     * 把最基础的content对象封装成VO对象，代码简单，但内容较多，分离一下代码更美观。
     *
     * @param page
     * @param contentId
     * @return
     */
    private List<ContentVO> getContentVOLinkList(Page<Content> page, Long contentId) {
        List<ContentVO> contentVOList;
        List<Long> parentList = getContentParentPaths(contentId);
        IPage<Content> contentLink = contentMapper.getContentLink(page, contentId, parentList);
        List<Content> contentList = contentLink.getRecords();
        contentVOList = getContentVOListByContentList(contentList);
        return contentVOList;
    }

    /**
     * 通过获得contentList把content包装成contentVO，因为有多个方法要共用这一段代码，所以分离一下。
     * @param contentList
     * @return
     */
    private List<ContentVO> getContentVOListByContentList(List<Content> contentList) {

        List<ContentVO> contentVOList = new LinkedList<>();

        List<Long> contentIdList = getContentIdListByContentList(contentList);

        Map<Long, Integer> contentLikeMap = likesService.getContentLikeNumsMap(contentIdList);
        Map<Long, Integer> contentCommentMap = getContentCommentNumsMap(contentIdList);
        Map<Long, Integer> contentTranspondMap = transpondContentService.getContentTranspondNumsMap(contentIdList);
        Map<Long, Integer> contentQuoteMap = getContentQuoteNumsMap(contentIdList);

        for (Content c : contentList) {

            Integer commentNums = contentCommentMap.get(c.getId());
            Integer likeNums = contentLikeMap.get(c.getId());
            Integer transpondNums = contentTranspondMap.get(c.getId());
            Integer quoteNums = contentQuoteMap.get(c.getId());

            ContentVO contentVO = new ContentVO();
            contentVO.setContent(c);
            if (commentNums != null) {
                contentVO.setCommentNums(commentNums);
            } else {
                contentVO.setCommentNums(0);
            }
            if (likeNums != null) {
                contentVO.setLikeNums(likeNums);
            } else {
                contentVO.setLikeNums(0);
            }
            if (transpondNums != null) {
                contentVO.setTranspondNums(transpondNums);
            } else {
                contentVO.setTranspondNums(0);
            }
            if (quoteNums != null) {
                contentVO.setQuoteNums(quoteNums);
            } else {
                contentVO.setQuoteNums(0);
            }

            contentVOList.add(contentVO);
        }

        return contentVOList;
    }


    @Override
    public List<Long> getContentParentPaths(Long contentId) {
        Content content = contentMapper.selectById(contentId);
        String paths = content.getParentPaths();
        return ListUtils.getListByString(paths);
    }


    @Override
    public Map<Long, Integer> getContentCommentNumsMap(List<Long> contentIdList) {
        return contentMapper.getContentCommentMap(contentIdList);
    }

    @Override
    public Map<Long, Integer> getContentQuoteNumsMap(List<Long> contentIdList) {
        return contentMapper.getContentQuoteMap(contentIdList);
    }

    @Override
    public IPage<String> getContentQuoteUsernamePage(Page<String> page, Long contentId) {
        return contentMapper.getContentQuoteUsernamePage(page, contentId);
    }
}
