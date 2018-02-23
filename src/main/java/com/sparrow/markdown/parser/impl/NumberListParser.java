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
package com.sparrow.markdown.parser.impl;

import com.sparrow.constant.CONSTANT;
import com.sparrow.constant.REGEX;
import com.sparrow.constant.magic.CHAR_SYMBOL;
import com.sparrow.constant.magic.SYMBOL;
import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.mark.MarkEntity;
import com.sparrow.markdown.mark.TagListEntity;
import com.sparrow.markdown.parser.MarkParser;
import com.sparrow.utility.CollectionsUtility;
import com.sparrow.utility.StringUtility;
import com.sun.org.apache.regexp.internal.RE;
import java.util.ArrayList;
import java.util.List;

import static com.sparrow.markdown.mark.MarkContext.BORROWABLE_BLANK;

/**
 * @author harry
 */
public class NumberListParser implements MarkParser {

    @Override
    public boolean detectStartMark(MarkContext markContext) {
        int tempPointer = markContext.getCurrentPointer();
        String content = markContext.getContent();
        //the first letter must be \n
        if (content.charAt(tempPointer) != '\n') {
            //borrowable blank shared with last blank
            if (!BORROWABLE_BLANK.contains(this.mark())) {
                return false;
            }
            if (tempPointer < 1) {
                return false;
            }

            if (content.charAt(--tempPointer) != '\n') {
                return false;
            }
        }
        //the next letter must be digit
        tempPointer++;
        String digit = StringUtility.getDigit(content, tempPointer);
        if (digit.length() == 0) {
            return false;
        }
        tempPointer += digit.length();
        //the next letter must be .
        if (content.charAt(tempPointer) != CHAR_SYMBOL.DOT) {
            return false;
        }
        //next letter must by ' '
        tempPointer++;
        if (content.charAt(tempPointer) != CHAR_SYMBOL.BLANK) {
            return false;
        }
        markContext.setDetectingMarkStartPointer(tempPointer);
        return true;
    }

    private MarkEntity nextTagBegin(MarkContext markContext, int pointer) {
        int currentPointer = markContext.getCurrentPointer();
        String innerContent = markContext.getContent();
        for (MARK mark : MarkContext.CONTAINER) {
            if (mark.equals(this.mark())) {
                continue;
            }
            if (!innerContent.startsWith(mark.getStart(), pointer)) {
                continue;
            }
            int tempPointer = markContext.getCurrentPointer();
            markContext.setPointer(pointer);
            MarkEntity markEntity = MarkContext.MARK_PARSER_MAP.get(mark).validate(markContext);
            if (markEntity == null) {
                markContext.setPointer(tempPointer);
                continue;
            }
            System.out.println(mark);
            markContext.setPointer(currentPointer);
            markContext.setNextMark(markEntity);
            return MarkEntity.createCurrentMark(this.mark(), markContext.getCurrentPointer());
        }
        return null;
    }

    private TagListEntity validate(MarkContext markContext, TagListEntity currentEntity, String line, String next) {
        if (line.equals(CONSTANT.ENTER_TEXT_N) && next.equals(CONSTANT.ENTER_TEXT_N)) {
            return null;
        }

        if (line.equals(CONSTANT.ENTER_TEXT_N)) {
            return currentEntity;
        }

        String innerLine = line.trim();
        String digit = StringUtility.getDigit(innerLine, 0);
        //not start with digit
        if (digit.length() == 0) {
            currentEntity.setContent(currentEntity.getContent() + markContext.getInnerHtml(this.mark(),innerLine));
            return currentEntity;
        }

        //the next letter must be .
        if (innerLine.charAt(digit.length()) != CHAR_SYMBOL.DOT) {
            currentEntity.setContent(currentEntity.getContent() + markContext.getInnerHtml(this.mark(),innerLine));
            return currentEntity;
        }

        //the next letter must be ' '
        if (innerLine.charAt(digit.length() + 1) != CHAR_SYMBOL.BLANK) {
            currentEntity.setContent(currentEntity.getContent() + markContext.getInnerHtml(this.mark(),innerLine));
            return currentEntity;
        }

        int indent = StringUtility.getPrefixCount(line, "   ");
        TagListEntity parent = this.getParent(currentEntity, indent);
        if (parent == null) {
            currentEntity.setContent(currentEntity.getContent() + innerLine);
            return currentEntity;
        }

        TagListEntity newEntity = new TagListEntity();
        newEntity.setParent(parent);
        newEntity.setIndent(indent);
        newEntity.setTitle(digit);

        String innerHTML = markContext.getInnerHtml(this.mark(),innerLine.substring(digit.length() + 1).trim());
        newEntity.setContent(innerHTML.replaceAll(REGEX.TAG_BR, ""));
        parent.getChildren().add(newEntity);

        if (this.nextTagBegin(markContext, markContext.getCurrentPointer() + line.length()) != null) {
            return null;
        }
        return newEntity;
    }

    private TagListEntity getParent(TagListEntity current, Integer intent) {
        //brother
        if (intent == current.getIndent()) {
            return current.getParent();
        }
        //parent
        if (intent < current.getIndent()) {
            int count = current.getIndent() - intent;
            do {
                current = current.getParent();
            }
            while (count-- > 0);
            return current;
        }
        //children
        if (intent - current.getIndent() == 1) {
            return current;
        }
        return null;
    }

    @Override
    public MarkEntity validate(MarkContext markContext) {
        //skip first enter
        markContext.skipPointer(1);
        String line = markContext.readLine(markContext.getCurrentPointer());
        String next = markContext.readLine(markContext.getCurrentPointer() + line.length());
        TagListEntity current = new TagListEntity();
        current.setIndent(-1);
        TagListEntity parentEntity = current;
        while ((current = this.validate(markContext, current, line, next)) != null) {
            markContext.skipPointer(line.length());
            line = next;
            int nextPointer = markContext.getCurrentPointer() + line.length();
            next = markContext.readLine(nextPointer);
        }
        MarkEntity markEntity = MarkEntity.createCurrentMark(this.mark(), markContext.getCurrentPointer());
        markEntity.setTagListEntities(parentEntity.getChildren());
        return markEntity;
    }

    private String parseTagList(List<TagListEntity> tags, Integer intent) {
        StringBuilder ol = new StringBuilder();
        for (TagListEntity tag : tags) {
            ol.append(String.format("<li>%1$s</li>\n", tag.getContent()));
            if (!CollectionsUtility.isNullOrEmpty(tag.getChildren())) {
                ol.append(this.parseTagList(tag.getChildren(), tag.getIndent()));
            }
        }
        if (ol.length() > 0) {
            ol.insert(0, String.format("<ol class=\"ol%1$s\">\n", intent==null?"":"_"+intent));
            ol.append("</ol>\n");
        }
        return ol.toString();
    }

    @Override
    public void parse(MarkContext markContext) {
        List<TagListEntity> tagListEntities = markContext.getCurrentMark().getTagListEntities();
        markContext.append(this.parseTagList(tagListEntities,null));
        markContext.setPointer(markContext.getCurrentMark().getEnd());
    }

    @Override
    public MARK mark() {
        return MARK.NUMBER_LIST;
    }
}
