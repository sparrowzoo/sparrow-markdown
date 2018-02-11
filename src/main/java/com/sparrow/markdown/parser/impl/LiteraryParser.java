package com.sparrow.markdown.parser.impl;

import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;

/**
 * @author harry
 * @date 2018/2/6
 */
public class LiteraryParser implements MarkParser {

    @Override
    public void parse(MarkContext markContext) {
        int endIndex=  markContext.detectStartMarkAsPreviousEnd(MarkContext.CONTAINER,markContext.getExceptMark());
        int startIndex = markContext.getCurrentPointer();
        String content = markContext.getContent().substring(startIndex, endIndex);
        markContext.setPointer(endIndex);
        markContext.append(String.format(this.mark().getFormat(),content));
        markContext.clearCurrentMark();
    }
    @Override
    public MARK mark() {
        return MARK.LITERARY;
    }
}
