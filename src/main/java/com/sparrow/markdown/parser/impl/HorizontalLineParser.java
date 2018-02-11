package com.sparrow.markdown.parser.impl;

import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;

/**
 * @author harry
 * @date 2018/2/6
 */
public class HorizontalLineParser implements MarkParser {
    @Override public int validate(MarkContext mark) {
        return 0;
    }

    @Override
    public void parse(MarkContext markContext) {
        String title= markContext.readLine(markContext.getCurrentPointer()+1);
        String line=markContext.readLine(markContext.getCurrentPointer()+title.length());
        if(line.equals(this.mark().getEnd())){
            markContext.append(String.format(this.mark().getFormat(),title));
            markContext.clearCurrentMark();
            markContext.setPointer(markContext.getCurrentPointer()+title.length()+line.length());
            return;
        }
        markContext.setExceptMark(this.mark());
        MarkContext.MARK_PARSER_MAP.get(MARK.LITERARY).parse(markContext);
    }


    @Override public MARK mark() {
        return MARK.HORIZONTAL_LINE;
    }
}
