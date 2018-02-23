package com.sparrow.markdown.parser.impl;

import com.sparrow.constant.magic.DIGIT;
import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.mark.MarkEntity;
import com.sparrow.markdown.parser.MarkParser;

import java.util.List;

/**
 * @author harry
 */
public class LiteraryParser implements MarkParser {

    @Override public boolean detectStartMark(MarkContext markContext) {
        return true;
    }

    @Override
    public MarkEntity validate(MarkContext markContext) {
        int currentPointer = markContext.getCurrentPointer();
        MarkEntity nextMark=null;

        int minIndex=markContext.getContentLength();
        String innerContent=markContext.getContent();
        for (MARK mark : MarkContext.CONTAINER) {
            markContext.setPointer(currentPointer+1);
            while (markContext.getCurrentPointer() < innerContent.length()) {
                if (mark.equals(this.mark())) {
                    break;
                }
                int start = innerContent.indexOf(mark.getStart(), markContext.getCurrentPointer());
                if (start < markContext.getCurrentPointer()) {
                    break;
                }
                if (start >= minIndex) {
                    break;
                }
                markContext.setPointer(start);
                MarkEntity markEntity=MarkContext.MARK_PARSER_MAP.get(mark).validate(markContext);
                if (markEntity!=null) {
                    System.out.println(mark);
                    nextMark=markEntity;
                    minIndex = start;

                    innerContent=innerContent.substring(0,minIndex);
                    //多一个标签至少需要2个字符
                    if (start == currentPointer + 1) {
                        markContext.setPointer(currentPointer);
                        markContext.setNextMark(nextMark);
                        return MarkEntity.createCurrentMark(this.mark(),minIndex);
                    }
                }

                markContext.skipPointer(mark.getStart().length());
            }
        }
        markContext.setPointer(currentPointer);
        markContext.setNextMark(nextMark);
        return MarkEntity.createCurrentMark(this.mark(),minIndex);
    }

    @Override
    public void parse(MarkContext markContext) {
        String content = markContext.getContent().substring(markContext.getCurrentPointer(), markContext.getCurrentMark().getEnd());
        markContext.setPointer(markContext.getCurrentMark().getEnd());
        markContext.append(String.format(this.mark().getFormat(), content.replaceAll("\n+","<br/>")));
        markContext.clearCurrentMark();
    }

    @Override
    public MARK mark() {
        return MARK.LITERARY;
    }
}
