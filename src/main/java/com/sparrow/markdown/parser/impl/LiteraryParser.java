package com.sparrow.markdown.parser.impl;

import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.mark.MarkWithIndex;
import com.sparrow.markdown.parser.MarkParser;

/**
 * @author harr
 * @date 2018/2/6
 */
public class LiteraryParser implements MarkParser {

    @Override
    public void parse(MarkContext markContext) {
//        int endMarkIndex = this.getContent().indexOf(mark.getEnd(), this.getCurrentMarkStartPointer()+mark.getStart().length());
//        if (endMarkIndex > 0) {
//            String content = this.content.substring(this.currentPointer+mark.getStart().length(), endMarkIndex);
//            MarkContext innerContext = new MarkContext(content);
//            this.setPointer(endMarkIndex + mark.getEnd().length());
//            MarkdownParserComposite.getInstance().parse(innerContext);
//            this.append(String.format(mark.getFormat(), innerContext.getHtml()));
//            return;
//        }
        //MarkContext.MARK_PARSER_MAP.get(MARK.LITERARY).parse(this);
    }


    @Override
    public MARK mark() {
        return MARK.LITERARY;
    }
}
