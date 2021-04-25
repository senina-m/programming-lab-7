package ru.senina.itmo.lab7;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.senina.itmo.lab7.labwork.LabWork;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

/**
 * Class to keep collection's elements
 */
public class CollectionKeeper {
    @JsonCreator
    public CollectionKeeper() {
    }

    @JsonIgnore
    private final Date time = new Date();
    //TODO: think how to save creation time for full table (do i actually need it?)

//--------------------------METHODS----------------------------------------------------------------------


    public void setList(List<LabWork> list) throws IllegalArgumentException {
        for (LabWork element : list) {
            DBManager.addElement(element);
        }
    }

    public List<LabWork> getList() {
        return DBManager.readAll();
    }


    @JsonIgnore
    public int getAmountOfElements() {
        return Optional.of(DBManager.countNumOfElements()).orElse(0);
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        return dateFormat.format(time);
    }

    public String updateID(long id, LabWork element) {
        try {
            DBManager.updateById(element, id);
            return "Element with id: " + id + " was successfully updated.";
        } catch (Exception e) {
            return "There is no element with id: " + id + " in collection.";
            //todo: possess different exceptions
        }
    }

    public String add(LabWork element) {
        try {
            DBManager.addElement(element);
            return "Element with id: " + element.getId() + " was successfully added.";
        } catch (Exception e) {
            Logging.log(Level.WARNING, "Something wrong with adding element to collection. (Warning from collectionKeeper)" + e.getMessage());
            //todo: possess different exceptions
            throw new RuntimeException("Something wrong with adding element to collection. (Warning from collectionKeeper) " + e.getMessage());
        }
    }

    public String removeById(long id) {
        try {
            return "Element with id: " + id + " was successfully removed.";
        }catch (Exception e){
            return "There is no element with id: " + id + " in collection.";
            //todo: possess different exceptions
        }
    }

    public String clear() {
        try {
            DBManager.clear();
            return "The collection was successfully cleared.";
        }catch (Exception e){
            Logging.log(Level.WARNING, "Something wrong with clearing of collection. (Warning from collectionKeeper)" + e.getMessage());
            //todo: possess different exceptions
            throw new RuntimeException("Something wrong with clearing of collection. (Warning from collectionKeeper) " + e.getMessage());
        }
    }

    public String removeAt(int index) {
        try {
            DBManager.removeATIndex(index);
            return "Element with index " + index + " was successfully removed.";
        } catch (Exception e) {
            //todo: possess different exceptions
            return "Removing an element with index " + index + " was failed. No such index in the collection.";
        }
    }


    public String sort() {
        //todo: дописать проверку пуста ли коллеция (?)
        try {
            DBManager.sort();
            return "Collection was successfully sort.";
        }catch (Exception e){
            Logging.log(Level.WARNING, "Something wrong with sorting collection. (Warning from collectionKeeper)" + e.getMessage());
            //todo: possess different exceptions
            throw new RuntimeException("Something wrong with sorting collection. (Warning from collectionKeeper) " + e.getMessage());
        }
    }


    public String removeGreater(LabWork element) {
        try {
            DBManager.removeGreater(element);
            return "All elements greater then entered were successfully removed.";
        }catch (Exception e){
            Logging.log(Level.WARNING, "Something wrong with removing_greater elements of collection. (Warning from collectionKeeper)" + e.getMessage());
            //todo: possess different exceptions
            throw new RuntimeException("Something wrong with removing_greater elements of collection. (Warning from collectionKeeper) " + e.getMessage());
        }
    }


    public LabWork minByDifficulty() throws IndexOutOfBoundsException {
        try {
            return DBManager.minByBifficulty();
        } catch (Exception e) {
            Logging.log(Level.WARNING, "Something wrong with minByDifficulty elements of collection. (Warning from collectionKeeper)" + e.getMessage());
            //todo: possess different exceptions
            throw new InvalidArgumentsException("No elements in collection. Can't choose the less by Difficulty.");
        }
    }

    public List<LabWork> filterByDescription(String description) {
        try{
            return DBManager.filterByDescription(description);
        }catch (Exception e){
            Logging.log(Level.WARNING, "Something wrong with filterByDescription of collection. (Warning from collectionKeeper)" + e.getMessage());
            //todo: possess different exceptions
            throw new RuntimeException("Something wrong with filterByDescription of collection. (Warning from collectionKeeper) " + e.getMessage());
        }
    }


    @JsonIgnore
    public List<LabWork> getSortedList() {
        try {
            DBManager.sort();
            return DBManager.readAll();
        }catch (Exception e){
            Logging.log(Level.WARNING, "Something wrong with getSortedList of collection. (Warning from collectionKeeper)" + e.getMessage());
            //todo: possess different exceptions
            throw new RuntimeException("Something wrong with getSortedList of collection. (Warning from collectionKeeper) " + e.getMessage());
        }
    }

}
