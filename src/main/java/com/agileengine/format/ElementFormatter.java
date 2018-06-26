package com.agileengine.format;

import org.jsoup.nodes.Element;

import static java.util.stream.Collectors.joining;

public final class ElementFormatter {

    private ElementFormatter() {
    }

    public static String format(Element element) {
        return element.parents().stream()
            .sorted((el1, el2) -> -1)
            .map(parent -> parent.tag().getName() + getAttrs(parent))
            .collect(joining(" > "));
    }

    private static String getAttrs(Element element) {
        if (!element.attributes().hasKey("id") && !element.attributes().hasKey("class")) {
            return "";
        }

        String attributes = "";

        if (element.hasAttr("id")) {
            attributes += "id=" + element.attr("id");
        }
        if (element.hasAttr("class")) {
            attributes += "class=" + element.attr("class");
        }

        return "[" + attributes + "]";
    }
}
