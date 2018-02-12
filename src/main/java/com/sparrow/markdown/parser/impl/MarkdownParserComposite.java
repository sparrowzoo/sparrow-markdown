package com.sparrow.markdown.parser.impl;

import com.sparrow.constant.magic.DIGIT;
import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.mark.MarkEntity;
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

    @Override
    public MarkEntity validate(MarkContext mark) {
        return null;
    }

    @Override
    public void parse(MarkContext markContext) {
        do {
            //detect start mark
            if (markContext.getNextMark() != null) {
                markContext.setCurrentMark(markContext.getNextMark());
                markContext.setNextMark(null);
            } else {
                markContext.detectStartMark(markContext.getParentMark());
            }
            if (markContext.getCurrentMark() != null) {
                MarkContext.MARK_PARSER_MAP.get(markContext.getCurrentMark().getMark()).parse(markContext);
                markContext.clearCurrentMark();
                continue;
            }

            MarkParser literaryParse = MarkContext.MARK_PARSER_MAP.get(MARK.LITERARY);
            MarkEntity markEntity = literaryParse.validate(markContext);
            if (markEntity!=null) {
                markContext.setCurrentMark(markEntity);
                //按文本处理
                literaryParse.parse(markContext);
            }
        }
        while (markContext.getCurrentPointer() < markContext.getContentLength());
    }

    @Override
    public MARK mark() {
        return null;
    }
}
