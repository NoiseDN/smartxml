package com.agileengine.function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.BiFunction;

public class CssSelectorFunction implements BiFunction<File, String, Optional<Element>> {

    private static String CHARSET_NAME = "utf8";

    @Override
    public Optional<Element> apply(File htmlFile, String cssQuery) {
        try {
            Document doc = Jsoup.parse(
                htmlFile,
                CHARSET_NAME,
                htmlFile.getAbsolutePath());

            return Optional.ofNullable(doc.selectFirst(cssQuery));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
