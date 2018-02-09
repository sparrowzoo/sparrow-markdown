/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.markdown.mark;

import com.sparrow.constant.CONSTANT;
import com.sparrow.constant.magic.SYMBOL;
import com.sparrow.markdown.parser.MarkParser;
import com.sparrow.markdown.parser.impl.*;

import java.util.*;

/**
 * @author by harry
 */
public class MarkContext {
    public MarkContext(String content, Boolean isLine) {
        this.content = content;
        this.currentPointer = 0;
        this.contentLength = this.content.length();
        this.setDetectLine(isLine);
    }

    private static final int MARK_COUNT =32;
    public static final List<MARK> CONTAINER = new ArrayList<MARK>(MARK_COUNT);
    public static final Map<MARK, MarkParser> MARK_PARSER_MAP = new HashMap<MARK, MarkParser>(MARK_COUNT);
    public static final Map<MARK, List<MARK>> CHILD_MARK_PARSER = new HashMap<MARK, List<MARK>>(MARK_COUNT);

    /**
     * sort by key length
     */
    static {
        CONTAINER.add(MARK.H6);
        CONTAINER.add(MARK.H5);
        CONTAINER.add(MARK.H4);
        CONTAINER.add(MARK.H3);
        CONTAINER.add(MARK.H2);
        CONTAINER.add(MARK.H1);
        CONTAINER.add(MARK.BOLD);
        CONTAINER.add(MARK.ITALIC);

        CONTAINER.add(MARK.CODE);
        CONTAINER.add(MARK.HORIZONTAL_LINE);
        CONTAINER.add(MARK.QUOTE);
        CONTAINER.add(MARK.CHECK_BOX);
        CONTAINER.add(MARK.DISABLE_CHECK_BOX);

        CONTAINER.add(MARK.HIGHLIGHT);
        CONTAINER.add(MARK.UNDERLINE);
        CONTAINER.add(MARK.ERASURE);


        MARK_PARSER_MAP.put(MARK.H1, new H1Parser());
        MARK_PARSER_MAP.put(MARK.H2, new H2Parser());
        MARK_PARSER_MAP.put(MARK.H3, new H3Parser());
        MARK_PARSER_MAP.put(MARK.H4, new H4Parser());
        MARK_PARSER_MAP.put(MARK.H5, new H5Parser());
        MARK_PARSER_MAP.put(MARK.H6, new H6Parser());
        MARK_PARSER_MAP.put(MARK.HORIZONTAL_LINE, new HorizontalLineParser());
        MARK_PARSER_MAP.put(MARK.QUOTE, new QuoteParser());
        MARK_PARSER_MAP.put(MARK.CHECK_BOX, new CheckboxParser());
        MARK_PARSER_MAP.put(MARK.DISABLE_CHECK_BOX, new DisableCheckboxParser());
        MARK_PARSER_MAP.put(MARK.CODE, new CodeParser());
        MARK_PARSER_MAP.put(MARK.HIGHLIGHT, new HighlightParser());
        MARK_PARSER_MAP.put(MARK.UNDERLINE, new UnderlineParser());
        MARK_PARSER_MAP.put(MARK.ERASURE, new ErasureParser());
        MARK_PARSER_MAP.put(MARK.LITERARY, new LiteraryParser());
        MARK_PARSER_MAP.put(MARK.ITALIC, new ItalicParser());
        MARK_PARSER_MAP.put(MARK.BOLD, new BoldParser());

        CHILD_MARK_PARSER.put(MARK.H1, Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.H2, Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.H3, Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.H4, Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.H5, Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.H6, Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.HORIZONTAL_LINE, null);
        CHILD_MARK_PARSER.put(MARK.QUOTE,Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.CHECK_BOX,Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.DISABLE_CHECK_BOX,Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HIGHLIGHT,MARK.HIGHLIGHT,MARK.IMAGE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.CODE,null);
        CHILD_MARK_PARSER.put(MARK.HIGHLIGHT,Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.UNDERLINE,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.UNDERLINE,Arrays.asList(MARK.BOLD, MARK.ITALIC,MARK.ERASURE,MARK.HIGHLIGHT,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.BOLD,Arrays.asList(MARK.UNDERLINE, MARK.ITALIC,MARK.ERASURE,MARK.HIGHLIGHT,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.ITALIC,Arrays.asList(MARK.UNDERLINE, MARK.BOLD,MARK.ERASURE,MARK.HIGHLIGHT,MARK.HYPER_LINK));
        CHILD_MARK_PARSER.put(MARK.IMAGE,null);
        CHILD_MARK_PARSER.put(MARK.HYPER_LINK,Arrays.asList(MARK.UNDERLINE, MARK.BOLD,MARK.ERASURE,MARK.HIGHLIGHT,MARK.ITALIC));
    }

    private boolean detectLine;
    private int contentLength;
    /**
     * 当前字符指针
     */
    private int currentPointer;
    private String content = null;
    private StringBuilder html = new StringBuilder(8000);
    /**
     * 当前mark
     */
    private MARK currentMark;

    public int getCurrentPointer() {
        return currentPointer;
    }

    public void skipPointer(int currentPointer) {
        this.currentPointer += currentPointer;
    }

    public void setPointer(int currentPointer) {
        this.currentPointer = currentPointer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MARK getCurrentMark() {
        return currentMark;
    }

    public void setCurrentMark(MARK currentMark) {
        this.currentMark = currentMark;
    }

    public void clearCurrentMark() {
        this.currentMark = null;
    }

    public int getContentLength() {
        return contentLength;
    }


    public String getHtml() {
        return html.toString();
    }

    public void append(String html) {
        this.html.append(html);
    }

    public String readLine() {
        StringBuilder line = new StringBuilder(100);
        char enter = '\n';
        char current;
        while ((current = this.content.charAt(currentPointer)) != enter) {
            line.append(current);
        }
        return line.toString();
    }

    public boolean isDetectLine() {
        return detectLine;
    }

    public void setDetectLine(boolean detectLine) {
        this.detectLine = detectLine;
    }


    public void parse(MarkContext markContext, MARK mark) {
        int endMarkIndex = markContext.getContent().indexOf(mark.getEnd(), markContext.getCurrentPointer());
        if (endMarkIndex > 0) {
            String content = this.content.substring(this.currentPointer, endMarkIndex);
            MarkContext innerContext = new MarkContext(content, mark.isLine());
            this.setPointer(endMarkIndex + mark.getEnd().length());
            MarkdownParserComposite.getInstance().parse(innerContext);
            markContext.append(String.format(mark.getFormat(), innerContext.getHtml()));
            return;
        }
        MarkContext.MARK_PARSER_MAP.get(MARK.LITERARY).parse(markContext);
    }


    public MarkWithIndex detectStartMark(Boolean isLine,List<MARK> container) {
        if (this.getCurrentMark() != null) {
            return null;
        }
        for (MARK mark : container) {
            int index = content.indexOf(mark.getStart(), currentPointer);
            if (index < 0) {
                continue;
            }
            if (isLine != null && isLine != mark.isLine()) {
                continue;
            }
            return new MarkWithIndex(mark, index);
        }
        return null;
    }
}
