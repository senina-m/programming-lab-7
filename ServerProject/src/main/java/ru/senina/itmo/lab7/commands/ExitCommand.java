package ru.senina.itmo.lab7.commands;


import ru.senina.itmo.lab7.CollectionKeeper;
import ru.senina.itmo.lab7.CollectionKeeperParser;

@CommandAnnotation(name = "exit", collectionKeeper = true, parser = true)
public class ExitCommand extends CommandWithoutArgs{
    private CollectionKeeper collectionKeeper;
    private CollectionKeeperParser parser;
    public ExitCommand() {
        super("exit", "end the program (without saving to file)");
    }

    public void setCollectionKeeper(CollectionKeeper collectionKeeper) {
        this.collectionKeeper = collectionKeeper;
    }

    public void setParser(CollectionKeeperParser parser) {
        this.parser = parser;
    }

    @Override
    protected String doRun() {
        return parser.fromObjectToString(collectionKeeper);
    }
}
