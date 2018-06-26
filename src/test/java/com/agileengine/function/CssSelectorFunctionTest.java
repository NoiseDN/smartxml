package com.agileengine.function;

import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

public class CssSelectorFunctionTest {

    @Test
    public void should_find_element_by_css_selector() throws URISyntaxException {
        File inputFile = new File(ClassLoader.getSystemResource("agileengine_samle_file.html").toURI());

        Optional<Element> searchResults = new CssSelectorFunction().apply(inputFile, "div[id=\"success\"] button[class*=\"btn-primary\"]");

        assertTrue(searchResults.isPresent());
    }
}
