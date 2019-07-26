package com.zyj;

import static org.junit.Assert.assertTrue;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.asciidoctor.*;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    String swaggerUrl="http://127.0.0.1:8089/v2/api-docs";
    String swaggerDir="target/swagger/swagger.json";
    String adocDir="target/adoc";
    String markdownDir="target/markdown";
    String htmlDir="target/html";


    /**
    *  测试在线swagger.json生成adoc
    *@Description:
    *@Author by zyj
    *@Date  2019/7/26 11:43
    *@version V1.0
    */
    @Test
    public void testSwagger2markupForAdoc(){
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        Swagger2MarkupConverter.from(URI.create(swaggerUrl))
//本地swagger.json
// Swagger2MarkupConverter.from(Paths.get(swaggerDir))
                .withConfig(config)
                .build()
                .toFile(Paths.get(adocDir+"/all"));

    }


    @Test
    public void testSwagger2markupForMarkdown(){
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        Swagger2MarkupConverter.from(URI.create(swaggerUrl))
//本地swagger.json
// Swagger2MarkupConverter.from(Paths.get(swaggerDir))
                .withConfig(config)
                .build()
                .toFile(Paths.get(markdownDir+"/all"));

    }


    @Test
    public void testAsciidoctorForHtml(){
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        Attributes attributes = new Attributes();
        attributes.setCopyCss(true);
        attributes.setLinkCss(false);
        attributes.setSectNumLevels(3);
        attributes.setAnchors(true);
        attributes.setSectionNumbers(true);
        attributes.setHardbreaks(true);
        attributes.setTableOfContents(Placement.LEFT);
        //SpringMvc集成时 可能会出现projectdir未找到 需要加入
        attributes.setAttribute("projectdir", System.getProperty("user.dir"));
        OptionsBuilder optionsBuilder = OptionsBuilder.options()
                .backend("html5")
                .docType("book")
                .headerFooter(true)
                .eruby("")
                .inPlace(true)
                .safe(SafeMode.UNSAFE)
                .mkDirs(true)
                .toDir(new File(htmlDir))
                .attributes(attributes);
        asciidoctor.convertFile(
                new File(adocDir+"/all.adoc"),
                optionsBuilder.get());
    }
}
