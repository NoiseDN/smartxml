package com.agileengine.parser;

import com.agileengine.function.ByIdFunction;
import com.agileengine.function.CssSelectorFunction;

import org.jsoup.nodes.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class SmartXmlParser {

    private final File file;
    private final ByIdFunction byIdFunction = new ByIdFunction();
    private final CssSelectorFunction cssSelectorFunction = new CssSelectorFunction();

    public SmartXmlParser(File file) {
        this.file = file;
    }

    public Optional<Element> getElementById(String id) {
        return byIdFunction.apply(file, id);
    }

    public Optional<Element> getElementByOtherElement(Element inputElement) {
        String tag = inputElement.tagName();
        String title = inputElement.attr("title");
        String className = inputElement.attr("class");
        String innerText = inputElement.text();
        String href = inputElement.attr("href");
        String onClick = inputElement.attr("onclick");

        List<Element> elements = new ArrayList<>();

        getElementByCssSelector(tag + ":contains(" + innerText + ")").ifPresent(elements::add);
        getElementByCssSelector(tag + "[title*=" + title + "]").ifPresent(elements::add);
        getElementByCssSelector(tag + "[href*=" + href + "]").ifPresent(elements::add);
        getElementByCssSelector(tag + "[onclick*=" + onClick + "]").ifPresent(elements::add);
        getElementByCssSelector(tag + "[class*=" + className + "]").ifPresent(elements::add);

        Map<Element, Integer> scores = elements.stream()
            .collect(toMap(Function.identity(), el -> toScore(inputElement, el)));

        Map.Entry<Element, Integer> highestScoreEntry = null;

        for (Map.Entry<Element, Integer> entry : scores.entrySet()) {
            if (highestScoreEntry == null || entry.getValue().compareTo(highestScoreEntry.getValue()) > 0) {
                highestScoreEntry = entry;
            }
        }

        return Optional.ofNullable(highestScoreEntry.getKey());
    }

    private int toScore(Element inputElement, Element element) {
        String tag = inputElement.tagName();
        String title = inputElement.attr("title");
        String className = inputElement.attr("class");
        String innerText = inputElement.text();
        String href = inputElement.attr("href");
        String onClick = inputElement.attr("onclick");

        int score = 0;

        if (element.attr("tag").equals(tag)) {
            score++;
        }
        if (element.attr("title").equals(title)) {
            score++;
        }
        if (element.attr("class").contains(className)) {
            score++;
        }
        if (element.text().equals(innerText)) {
            score++;
        }
        if (element.attr("href").equals(href)) {
            score++;
        }
        if (element.attr("onclick").contains(onClick)) {
            score++;
        }

        return score;
    }

    private Optional<Element> getElementByCssSelector(String cssSelector) {
        return cssSelectorFunction.apply(file, cssSelector);
    }

}
