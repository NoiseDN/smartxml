package com.agileengine.function;

import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

public class ByIdFunctionTest {

    @Test
    public void should_find_element_by_id() throws URISyntaxException {
        File inputFile = new File(ClassLoader.getSystemResource("agileengine_samle_file.html").toURI());

        Optional<Element> searchElement = new ByIdFunction().apply(inputFile, "sendMessageButton");

        assertTrue(searchElement.isPresent());
    }
}
