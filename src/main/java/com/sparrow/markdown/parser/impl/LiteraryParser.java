package com.sparrow.markdown.parser.impl;

import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;
import java.util.List;

/**
 * @author harry
 */
public class LiteraryParser implements MarkParser {

    @Override public int validate(MarkContext markContext) {
        if (markContext.getCurrentMark() == null) {
            return -1;
        }
        int currentPointer=markContext.getCurrentPointer();
        int minIndex = markContext.getContent().length();
        for (MARK mark : MarkContext.CONTAINER) {
            if (mark.equals(this.mark())) {
                continue;
            }
            int index = markContext.getContent().indexOf(mark.getStart(), markContext.getCurrentPointer() + 1);
            markContext.setPointer(index);
            if (index < minIndex && MarkContext.MARK_PARSER_MAP.get(mark).validate(markContext) > 0) {
                System.out.println(mark);
                minIndex = index;
            }
        }
        markContext.setPointer(currentPointer);
        return minIndex;
    }

    @Override
    public void parse(MarkContext markContext) {
        int startIndex = markContext.getCurrentPointer();
        String content = markContext.getContent().substring(startIndex, markContext.getEndPointer());
        markContext.setPointer(markContext.getEndPointer());
        markContext.append(String.format(this.mark().getFormat(), content));
        markContext.clearCurrentMark();
    }

    @Override
    public MARK mark() {
        return MARK.LITERARY;
    }
}
