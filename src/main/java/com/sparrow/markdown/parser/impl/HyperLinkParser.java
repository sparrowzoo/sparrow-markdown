package com.sparrow.markdown.parser.impl;

import com.sparrow.constant.magic.DIGIT;
import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.mark.MarkEntity;
import com.sparrow.markdown.parser.MarkParser;

/**
 * [link](url)
 *
 * @author harry
 */
public class HyperLinkParser extends AbstractWithUrlParser {

    @Override
    public MARK mark() {
        return MARK.HYPER_LINK;
    }
}
