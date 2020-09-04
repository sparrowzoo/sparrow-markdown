package com.sparrow.markdown.image;

import com.sparrow.constant.FILE;
import com.sparrow.constant.REGEX;
import com.sparrow.image.AbstractImageExtractor;
import com.sparrow.protocol.constant.CONSTANT;
import com.sparrow.protocol.dto.ImageDTO;
import com.sparrow.utility.CollectionsUtility;
import com.sparrow.utility.FileUtility;

import java.util.List;
import java.util.regex.Matcher;

public class MarkdownImageExtractor extends AbstractImageExtractor {
    @Override
    protected String getImageRegexMark() {
        return REGEX.TAG_MARKDOWN_IMAGE;
    }

    @Override
    protected String getImageUrl(Matcher matcher) {
        return matcher.group(2);
    }

    @Override
    protected String getRemark(Matcher matcher) {
        return matcher.group(1);
    }

    @Override
    public String replace(String content, List<ImageDTO> images) {
        if (CollectionsUtility.isNullOrEmpty(images)) {
            return content;
        }
        //替换为站内图片
        for (ImageDTO image : images) {
            if (image.getInner()) {
                continue;
            }
            String url = FileUtility.getInstance().getShufflePath(
                    image.getImageId(), image.getExtension(), true,
                    FILE.SIZE.BIG);
            content = content.replace(
                    image.getImageMark(), String.format(CONSTANT.IMAGE_MARKDOWN_MARK_FORMAT, image.getRemark(), url));
        }
        return content;
    }

    @Override
    public String filter(String content) {
        return content;
    }

    @Override
    public String restore(String content, List<ImageDTO> images) {
        return content;
    }
}
