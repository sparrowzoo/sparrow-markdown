package com.sparrow.markdown;

import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;
import com.sparrow.markdown.parser.impl.MarkdownParserComposite;
import com.sparrow.utility.FileUtility;

/**
 * Created by harry on 2018/2/8.
 */
public class ParserTest {

    public static void main(String[] args) {
        MarkContext markContext = new MarkContext(
            "\n" +
                "- 1\n" +
                "   - 1.1\n" +
                "       - 1.1.1\n" +
                "         - 1.1.1.1\n" +
                "            - 1.1.1.1.1\n" +
                "            - 1.1.1.1.2\n" +
                "           - 1.1.1.2\n" +
                "           - 1.1.1.3\n" +
                "       - 1.1.2\n" +
                "   - 1.2\n" +
                "   - 1.3\n" +
                "- 2\n" +
                "- 3\n" +
                "- 1\n" +
                "   - 1.1\n" +
                "       - 1.1.1\n" +
                "         - 1.1.1.1\n" +
                "            - 1.1.1.1.1\n" +
                "            - 1.1.1.1.2\n" +
                "           - 1.1.1.2\n" +
                "           - 1.1.1.3\n" +
                "       - 1.1.2\n" +
                "   - 1.2\n" +
                "   - 1.3\n" +
                "- 2\n" +
                "- 3\n" +
                "1. 11111111\n" +
                "2. 2\n" +
                "8. 3\n" +
                "   1. 3.1\n" +
                "       1. 3.1.1\n" +
                "       2. 3.1.2\n" +
                "       3. 3.1.3\n" +
                "   2. 3.2\n" +
                "       1. 3.2.1\n" +
                "sfsdf**sfasff**sfsdf==sfs==adf\n" +
                "4. 4.1 sfsdf\n" +
                "5. 5\n" +
                "5555555555555\n" +
                "header 1 | header 2\n" +
                "---|---\n" +
                "row 1 col 1 | row 1 col 2\n" +
                "row 2 col 1 | row 2 col 2\n" +
                "\n" +
                "```\n" +
                "math\n" +
                "E = mc^2\n" +
                "\n" +
                "E=MC^5\n" +
                "E=mc-5\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "```\n" +
                "\n" +
                "\n" +
                "```\n" +
                "print(\"ddd\")\n" +
                "代码段\n" +
                "pr++int++f(\"引号==是红==色\")\n" +
                "\n" +
                "p()有括号认为是方法 \n" +
                "class A {}\n" +
                "```\n" +
                "\n" +

                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "数学表达式\n" +
                ">2 \n" +
                "\n" +
                "title | title2\n" +
                "---|---\n" +
                "r1-1 | r 1-2\n" +
                "r2-1 | r 2-2\n" +
                "\n" + "### SSS\n" +
                ">引用\n" +
                "FSAF\n" +
                "ASFSDF\n" +
                "\n" +
                "sfsf\n" +
                "    sfsafsfd\n" +
                "afsaf\n" +
                "fsd\n" +
                "afsf\n" +
                "asfsdf\n" +
                "sfsaf\n" +
                "\n" +
                "# S\n" +
                "S\n" +
                "SSS\n" +
                "\n" +
                "\n" +
                "sfsaf\n" +
                "sfsf\n" +
                "asdf粗**\n" +
                "*SSS*\n" +
                "\n" +
                "\n" +
                "*倾\n" +
                "sfsf\n" +
                "safs\n" +
                "adf\n" +
                "sfsafd\n" +
                "sadf\n" +
                "斜\n" +
                "*\n" +
                "\n" +
                "![image](http://note.youdao.com/favicon.ico)\n" +
                "\n" +
                "~~删\n" +
                "除\n" +
                "safsda\n" +
                "ffas\n" +
                "fdsaf ~~\n" +
                "\n" +
                "++下划线fsfs \n" +
                "sdfsdf sfsf \n" +
                "sssss\n" +
                "sfsfsf\n" +
                "afsdf\n" +
                "sadfsaf++\n" +
                "\n" +
                "**234**\n" +
                "- [ ] 这里![图标](http://note.youdao.com/favicon.ico" +
                "      )*是复*选框\n" +
                " 这里是复选框内容\n" +
                " 这里是什么东\n" +

                "- [ ] 这里![图标](http://note.youdao.com/favicon.ico" +
                "      )*是复*选框\n" +
                " 这里是复选框内容\n" +
                " 这里是什么东西\n" +
                "\n" +
                "<html>\n" +
                "KLKJLKJLKJLK\n" +
                "</html>\n" +
                "\n" +
                "\n" +
                "OOO\n" +
                "---\n" +
                "\n" +
                "- [x] 这是啥？\n" +
                "\n" +
                "[超==链S==FS++FSFFSAD++FSDF接](http://note.youdao.com/)\n" +
                "\n" +
                "\n" +
                "- [x] DGDFGDGDGDFGDFDGDFG[li==n==k](http://note.youdao.com/)FDSFSFSDAFDS\n" +
                "SFDFSDF\n" +
                "FSDFDSF\n" +
                "SDFSDFSDFSFSDF\n" +
                "\n" +
                "SFSDFS\n" +
                "![DDDDDDD](http://note.youdao.com/favicon.ico)\n" +
                "\n" +
                "sdfsadfs\n" +
                "\n" +
                "    TAB\n" +
                "    sfsf\n" +
                "    dfsdf\n" +
                "    sss\n" +
                "    TAB ENDIO\n" +
                "ssJJLL\n" +
                "\n" +
                "[link](http://note.youdao.com/)\n" +
                "# H2s\n" +
                "fs\n" +
                "fsfsf\n" +
                "==高\n" +
                "亮==\n" +
                "# h1\n" +
                "## h2\n" +
                "\n" +
                "### h3sf*sfs*dfs![DDDDDDDDDDDDDDDDDDDDD](http://note.youdao.com/favicon.ico)++fs++af\n" +
                "\n" +
                "#### h4 \n" +
                "\n" +
                "##### h5\n" +
                "\n" +
                "###### h6sfs\n" +
                "sfsdf\n" +
                "\n" +
                "\n" +
                "**加 sf\n" +
                "sdfd\n" +
                "s**\n" +
                "\n" +
                "s\n" +
                "---\n" +
                "\n" +
                "# sadfdasfs## \n" +
                "sfsdf\n" +
                "\n" +
                ">==用==> sfsfs\n" +
                "\n" +
                "22222\n" +
                "\n" +
                "\n" +
                "afsfsf\n" +
                "---\n" +
                "fsfs\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "> 这里是引用[link](http://note.youdao.com/)回车换行\n" +
                "sfsdfsafsad~~f这回~~车行要要\n" +
                "要ds梦想成真 asdfsdfsfsf\n" +
                "\n" +
                "飘飘\n" +
                "\n" +
                "\n" +
                "\n" +
                "asfsdfsadfafsaf 这里是在北京，#hsafsdfsfasfd\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "sdfsfs\n" +
                "```\n" +
                "  @Override public MarkEntity validate(MarkContext markContext) {\n" +
                "        String line;\n" +
                "        //-4 represent tab key\n" +
                "        int pointer=markContext.getCurrentPointer()+this.mark().getStart().length()-4;\n" +
                "        int start=pointer;\n" +
                "        while ((line = markContext.readLine(pointer)).startsWith(\"    \")) {\n" +
                "            pointer += line.length();\n" +
                "        }\n" +
                "        MarkEntity markEntity = MarkEntity.createCurrentMark(this.mark(), pointer);\n" +
                "        markEntity.setContent(markContext.getContent().substring(start, pointer).replaceAll(\"\\n+\",\"<br/>\"));\n" +
                "        return markEntity;\n" +
                "    }\n" +
                "```\n" +
                "afddd\n" +
                "    \n" +
                "\n" +
                "        这里是北京**f这里是北京f这里是*北京f这*里是北京f*这里是*北京f这里是北京\n" +
                "    sfsfafsdf++dsff这里++是北京f这里是北京**f这里是北**京f这里是北京f这里是北京\n" +
                "    fsfsfsadff这里是北京f这里*是北京f*这++里是北京f这里是++北京\n" +
                "    fsafsafsfsfsfsa sdfasf afaf adfsaf f这里是北京f这里是北京**f这里是北京f这里是北京sfs \n" +
                "    \n" +
                "    sfsadf\n" +
                "    afsadf\n" +
                "    safsaf\n" +
                "    sfs\n" +
                "      fsfsaf\n" +
                "fsafssfsdfsfsfsffsafsafsf\n" +

                "\n" +
                "\n" +
                "SAFSFSF\n" +
                "\n" +
                "    AS++FDD++S\n" +
                "    sfasfsf\n" +
                "    a~~sf~~asdf\n" +
                "    afasf\n" +
                "sdfasfsfsfsdfsaf\n" +
                "sdfsadf\n" +
                "safsadfasdf\n" +
                "\n" +
                "\n" +
                "*F\n" +
                "SA\n" +
                "\n" +
                "DJHKJLJSF\n" +
                "A*\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "QWERTY==UIOP[]P==OIUYT==YUISFS[FSAFSA](http://note.youdao.com/)DFSA[DDDD](http://note.youdao.com/favicon.ico)DFSDAFSADFKWER==TYUIOP[ERTYUIOP]\n" +
                "\n" +
                "fsdfs\n" +
                "fasfas\n" +
                "dfsfsadf\n" +
                "\n"
        );
        MarkParser markParser = MarkdownParserComposite.getInstance();
        markParser.parse(markContext);
        FileUtility.getInstance().writeFile("/Users/harry/markdown.html", "<style>\n" +
            "    .bold{\n" +
            "        font-weight: bold;\n" +
            "    }\n" +
            "    .italic{\n" +
            "        font-style:italic;\n" +
            "    }\n" +
            "    .underline{\n" +
            "        text-decoration: none;\n" +
            "        border-bottom: 1px solid gray;\n" +
            "    }\n" +
            "    .erasure{\n" +
            "        text-decoration:line-through\n" +
            "    }\n" +
            "    .tab{\n" +
            "        border:1pc solid red;\n" +
            "    }\n" +
            "    .highlight{\n" +
            "        background-color: red;\n" +
            "    }\n" +
            "\n" +
            "    .quote{\n" +
            "        text-indent: 10px;\n" +
            "        border-left: 1px solid red;\n" +
            "    }\n" +
            "</style>" + markContext.getHtml());
    }
}
