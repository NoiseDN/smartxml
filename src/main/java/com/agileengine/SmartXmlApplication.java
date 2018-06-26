package com.agileengine;

import com.agileengine.format.ElementFormatter;
import com.agileengine.parser.SmartXmlParser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.Optional;

public class SmartXmlApplication {

    public static void main(String[] args) {
        Options options = new Options();

        Option input = new Option("o", "origin", true, "origin file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("n", "next", true, "next file path");
        output.setRequired(true);
        options.addOption(output);

        Option id = new Option("i", "id", true, "element id");
        id.setRequired(true);
        options.addOption(id);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String originFilePath = cmd.getOptionValue("origin");
        String nextFilePath = cmd.getOptionValue("next");
        String idStr = cmd.getOptionValue("id");

        System.out.println("\nSmart XML Parser v0.0.1\n");
        SmartXmlParser originalParser = new SmartXmlParser(new File(originFilePath));

        Optional<Element> foundElement = originalParser.getElementById(idStr);

        System.out.println("File: " + originFilePath);

        if (!foundElement.isPresent()) {
            System.out.println("Element with id " + id + " not found");
            System.exit(1);
        }

        System.out.println("Found node: " + foundElement.get());
        System.out.println("Path: " + ElementFormatter.format(foundElement.get()));
        
        Element button = foundElement.get();

        SmartXmlParser nextParser = new SmartXmlParser(new File(nextFilePath));

        Optional<Element> foundNextElement = nextParser.getElementByOtherElement(button);

        System.out.println("\nFile: " + nextFilePath);

        if (!foundNextElement.isPresent()) {
            System.out.println("Element similar to original not found");
            System.exit(1);
        }

        System.out.println("Found node: " + foundNextElement.get());
        System.out.println("Path: " + ElementFormatter.format(foundNextElement.get()));
    }

}
