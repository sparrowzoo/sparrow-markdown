package com.sparrow.markdown;

import com.sparrow.markdown.mark.MarkContext;
import com.sparrow.markdown.parser.MarkParser;
import com.sparrow.markdown.parser.impl.MarkdownParserComposite;
import com.sparrow.utility.FileUtility;

/**
 * Created by harry on 2018/2/8.
 */
public class ParserTest{

    public static void main(String[] args) {
        MarkContext markContext=new MarkContext("[link](http://note.youdao.com/)\n" +
                "# H2s\n" +
                "fs\n" +
                "fsfsf\n" +
                "==高\n" +
                "亮==\n" +
                "# h1\n" +
                "## h2\n" +
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
                "sfsdfsafsadf这回车行要要\n" +
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
                "sdfsadfs\n" +
                "\n" +
                "    sfsfs\n" +
                "    sfsf\n" +
                "    dfsdf\n" +
                "    sss\n" +
                "\n" +
                "sdfsfs\n" +
                "```\n" +
                "sfsfsf\n" +
                "```\n" +
                "\n" +
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
                "1. lkjlj\n" +
                "2. ijljlk\n" +
                "\n" +
                "\n" +
                "SAFSFSF\n" +
                "\n" +
                "    ASFDDS\n" +
                "    sfasfsf\n" +
                "    asfasdf\n" +
                "    afasf\n" +
                "sdfasfsfsfsdfsaf\n" +
                "sdfsadf\n" +
                "safsadfasdf\n" +
                "\n" +
                "\n" +
                "FSAD\n" +
                "F*SD*F\n" +
                "SAF\n" +
                "SAF\n" +
                "SADFAS\n" +
                "F\n" +
                "- SFAF\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "sfsf\n" +
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
                "sadf斜 \n" +
                "*\n" +
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
                "QWERTY==UIOP[]P==OIUYT==YUISFS[FSAFSA](http://note.youdao.com/)DFSA[DDDD](http://note.youdao.com/favicon.ico)DFSDAFSADFKWER==TYUIOP[ERTYUIOP]\n" +
                "\n" +
                "fsdfs\n" +
                "fasfas\n" +
                "dfsfsadf\n" +
                "\n" +
                "\n" +
                "横线\n" +
                "---\n" +
                "\n" +
                "### SSS\n" +
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
                "\n" +
                "s\n" +
                "\n" +
                "\n" +
                "- 列表点\n" +
                "\n" +
                "\n" +
                "\n" +
                "- 列表点\n" +
                "\n" +
                "1. 数sf++sfs++df字 \n" +
                "    1. 1.1\n" +
                "    2. 1.2\n" +
                "1. 数字3\n" +
                "    1. 2.2\n" +
                "**234**\n" +
                "- [ ] 这里![图标](http://note.youdao.com/favicon.ico\n" +
                ")*是复*选框\n" +
                " 这里是复选框内容\n" +
                " 这里是什么东西\n" +
                "- [x] 这是啥？\n" +
                "\n" +
                "[超==链S==FS++FSFFSAD++FSDF接](http://note.youdao.com/)\n" +
                "\n" +
                "\n" +
                "- [li==n==k](http://note.youdao.com/)\n" +
                "\n" +
                "![DDDDDDD](http://note.youdao.com/favicon.ico)\n" +
                "\n" +
                "```     print(\"ddd\")\n" +
                "代码段\n" +
                "pr++int++f(\"引号==是红==色\")\n" +
                "\n" +
                "p()有括号认为是方法 \n" +
                "class A {}\n" +
                "```\n" +
                "\n" +
                "\n" +
                "```math\n" +
                "E = mc^2\n" +
                "\n" +
                "E=MC^5\n" +
                "E=mc-5\n" +
                "\n" +
                "\n" +
                "```\n" +
                "数学表达式\n" +
                ">2 \n" +
                "\n" +
                "title | title2\n" +
                "---|---\n" +
                "r1-1 | r 1-2\n" +
                "r2-1 | r 2-2\n" +
                "\n");
        MarkParser markParser=MarkdownParserComposite.getInstance();
        markParser.parse(markContext);
        FileUtility.getInstance().writeFile("D:\\markdown.html","<style>\n" +
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
                "</style>"+markContext.getHtml());
    }
}
