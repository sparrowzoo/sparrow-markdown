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
import com.sparrow.constant.magic.SYMBOL;
import com.sparrow.markdown.mark.MARK;
import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.mark.MarkEntity;
import com.sparrow.markdown.parser.MarkParser;
import com.sparrow.utility.StringUtility;
import com.sun.org.apache.regexp.internal.RE;
import java.util.ArrayList;
import java.util.List;

/**
 * @author harry
 */
public class NumberListParser implements MarkParser {

    @Override
    public boolean detectStartMark(MarkContext markContext) {
        int tempPointer = markContext.getCurrentPointer();
        String content = markContext.getContent();
        //the first latter must be \n
        if (content.charAt(tempPointer) != '\n') {
            return false;
        }
        //the next latter must be digit
        tempPointer++;
        String digit = StringUtility.getDigit(content, tempPointer);
        if (digit.length() == 0) {
            return false;
        }
        tempPointer += digit.length();
        //the next latter must be .
        if (content.charAt(tempPointer) != SYMBOL.DOT.charAt(0)) {
            return false;
        }
        //next latter must by \n
        tempPointer++;
        if (content.charAt(tempPointer) != '\n') {
            return false;
        }
        markContext.setDetectingMarkStartPointer(tempPointer);
        return true;
    }

    private boolean validate(String line1, String line2) {
        if (line1.equals(CONSTANT.ENTER_TEXT_N) && line2.equals(CONSTANT.ENTER_TEXT_N)) {
            return false;
        }
        //todo

        return false;
    }

    @Override
    public MarkEntity validate(MarkContext markContext) {
        int pointer = markContext.getDetectingMarkStartPointer();
        String line = markContext.readLine(pointer);
        String next = markContext.readLine(pointer + line.length());
        //todo
        return null;
    }

    @Override
    public void parse(MarkContext markContext) {
        String[] titleArray = markContext.getCurrentMark().getTitleArray();
        List<String[]> tdList = markContext.getCurrentMark().getTdList();
        StringBuilder table = new StringBuilder(1024);
        table.append("<table><tr>");
        for (String title : titleArray) {
            table.append(String.format("<th>%1$s</th>", title));
        }
        table.append("<tr/>");
        for (String[] tdArray : tdList) {
            table.append("<tr>");
            for (String td : tdArray) {
                table.append(String.format("<td>%1$s</td>", td));
            }
            table.append("</tr>");
        }
        table.append("</table>");
        markContext.append(table.toString());
        markContext.setPointer(markContext.getCurrentMark().getEnd());
    }

    @Override
    public MARK mark() {
        return MARK.TABLE;
    }
}
