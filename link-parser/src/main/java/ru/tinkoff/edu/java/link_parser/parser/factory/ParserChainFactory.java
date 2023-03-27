package ru.tinkoff.edu.java.link_parser.parser.factory;

import ru.tinkoff.edu.java.link_parser.parser.EmptyParser;
import ru.tinkoff.edu.java.link_parser.parser.GitHubParser;
import ru.tinkoff.edu.java.link_parser.parser.Parser;
import ru.tinkoff.edu.java.link_parser.parser.StackOverflowParser;

public final class ParserChainFactory {

    public static Parser create() {
        Parser headNode = new GitHubParser();
        Parser nextNode = new StackOverflowParser();
        Parser lastNode = new EmptyParser();

        headNode.setNextParser(nextNode);
        nextNode.setNextParser(lastNode);

        return headNode;
    }
}
