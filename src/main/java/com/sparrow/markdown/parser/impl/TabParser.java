package com.sparrow.markdown.parser.impl;

import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;

/**
 * Created by harry on 2018/2/7.
 */
public class TabParser implements MarkParser {
    @Override
    public void parse(MarkContext markContext) {
        markContext.parse(this.mark());
    }

    @Override
    public MARK mark() {
        return MARK.TAB;
    }
}
