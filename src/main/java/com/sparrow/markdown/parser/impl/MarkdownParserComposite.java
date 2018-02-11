package com.sparrow.markdown.parser.impl;

import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;

import java.util.List;

/**
 * Created by harry on 2018/2/6.
 */
public class MarkdownParserComposite implements MarkParser {
    private static MarkdownParserComposite instance = new MarkdownParserComposite();

    private MarkdownParserComposite() {
    }

    public static MarkdownParserComposite getInstance() {
        return instance;
    }

    @Override public int validate(MarkContext mark) {
        return 1;
    }

    @Override
    public void parse(MarkContext markContext) {
        do {
            //detect start mark
            markContext.detectStartMark(markContext.getParentMark());
            if (markContext.getCurrentMark() != null) {
                MarkContext.MARK_PARSER_MAP.get(markContext.getCurrentMark()).parse(markContext);
                markContext.clearCurrentMark();
                continue;
            }
            markContext.setCurrentMark(MARK.LITERARY);
            MarkParser literaryParse=MarkContext.MARK_PARSER_MAP.get(MARK.LITERARY);
            literaryParse.validate(markContext);
            //按文本处理
            literaryParse.parse(markContext);
        }
        while (markContext.getCurrentPointer() < markContext.getContentLength());
    }

    @Override
    public MARK mark() {
        return null;
    }
}
