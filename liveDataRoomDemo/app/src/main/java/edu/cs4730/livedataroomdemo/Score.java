package edu.cs4730.livedataroomdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Score is Plain Old Java Object (POJO)  This needs to provide getters and setters for everything plus the
 * constructors for the object.   The table in the database is specified as scores
 */


@Entity(tableName = Score.TABLE_NAME)
public class Score {

    /**
     * The name of the Score table.
     */
    public static final String TABLE_NAME = "scores";

    /**
     * The name of the ID column.
     */
    public static final String COLUMN_ID = BaseColumns._ID;

    /**
     * The name of the name column.
     */
    public static final String COLUMN_NAME = "name";

    /**
     * The name of the score column.
     */
    public static final String COLUMN_SCORE = "score";

    /**
     * The unique ID each score data pair.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    /**
     * The name of the person.
     */
    @ColumnInfo(name = COLUMN_NAME)
    public String name;

    /**
     * The person's score.
     */
    @ColumnInfo(name = COLUMN_SCORE)
    public int score;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // blank constructor, the ignore is for database, so it doesn't choose this one.
    @Ignore
    public Score() {
    }

    // constructor for the 2 data points.
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //static method to get the data from ContentValues (for content provider data)
    public static Score fromContentValues(ContentValues values) {
        final Score scoreData = new Score();
        if (values.containsKey(COLUMN_ID)) {
            scoreData.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_NAME)) {
            scoreData.name = values.getAsString(COLUMN_NAME);
        }
        if (values.containsKey(COLUMN_SCORE)) {
            scoreData.score = values.getAsInteger(COLUMN_SCORE);
        }
        return scoreData;
    }

    //some names for the dummy data.
    static final String[] names = {"Adrian Pucey", "Alastor Mad Eye Moody", "Albus Dumbledore", "Alguff Awful", "Alice Longbottom", "Alicia Spinnet", "Ambrosius Flume", "Amelia Susan Bones",
        "Amos Diggory", "Andrew Kirke", "Andromeda Blackv", "Andros the Invincible", "Angelina Johnson", "Arabella Figg", "Argus Filch", "Arthur Weasley", "Augusta Longbottom",
        "Barny the Fruitbat", "Bartemius Crouch Jr.", "Bartemius Crouch Senior", "Belatrix Black", "Bellatrix Lestrange", "Bertha Jorkins", "Bertie Bott", "Bill Weasly",
        "Birtha Jorkins", "Blaise Zabini", "Bloody Barron", "Bob Ogden", "Cassius  Warrington", "Cedric Diggory", "Charlie Weasley", "Cho Chang", "Ciceron Harkis", "Colin Creevy",
        "Cormac McLaggen", "Cornelious Fudge", "Crookshanks", "Daphne Greengrass", "Dean Thomas", "Dedulus Diggle", "Dennis Creevey", "Dobby House Elf", "Dolores Umbrige", "Draco Malfoy",
        "Duddly Dersly", "Eddie Carmichael", "Edgar Bones", "Egor Karkeroff", "Eleanor Branstone", "Eloise Midgeon", "Emma Dobbs", "Emmaline Vance", "Ernie Mcmillan", "Ernie Prang", "Fat Fryer",
        "Fat Lady", "Fenrir Greyback", "Filius Flitwick", "Finius Nigelus", "Fleur Delacour", "Florean Fortesque", "Frank Bryce", "Fred Weasley", "Gabrielle Delacour", "George Weasley",
        "Gildaroy Lockheart", "Ginny Weasley", "Godric Gryffindor", "Graham Pritchard", "Gregory Goyle", "Gwenog Jones", "Hannah Abbott", "Harry Potter", "Hecter Dagworth Granger",
        "Helga Hufflepuff", "Herbert Chorley", "Hermione Jane Granger", "Herold Dingle", "Humphrey Belcher", "Igor Karkaroff", "James Potter", "Justin Finch Flechly", "Katie Bell",
        "Kingsley Shacklebolt", "Laura Madley", "Lavender Brown", "Lee Jordan", "Lily Potter", "Lord Voldemort", "Lucius Malfoy", "Ludovic Ludo Bagman",
        "Luna Lovegood", "Madam Hooch", "Madam Malkin", "Madam Pince", "Madam Pomfrey", "Madam Rosmerta", "Maddam Maxime", "Maddam Pomfrey", "Maddam Rosemerter", "Malcolm Baddock",
        "Marcus Belby", "Marcus Flint", "Marge Dursley", "Marietta Edgecome", "Mark Evans", "Marvalo Guant", "Merope Gaunt", "Micheal Corner", "Millicent Bagnold", "Millicent Bulstrode",
        "Minerva McGonagall", "Moaning Myrtle", "Molly Weasley", "Montague", "Morfin Gaunt", "Mundungus Fletcher", "Narcissa Malfoy", "Neville Longbottom", "Nicholas Flamel",
        "Nimphodorah Tonks", "Theodore Nott", "Nymphadora Tonks", "Olivander", "Oliver Wood", "Olympe Maxime", "Orla Quirke", "Otto Bagman", "Owen Cauldwell", "Padma Patil",
        "Pansy Parkinson", "Parvatie Patil", "Penelope Clearwater", "Percy Weasly", "Pertunia Dursly", "Peter Petigrue", "Petunia Dursley", "Piers Polkis", "Pigwigeon", "Severus Snape",
        "Remus Lupin", "Rits Skeeter", "Roger Davies", "Romilda Vane", "Ron Weasley", "Rose Zeller", "Rowena Ravenclaw", "Rubeus Hagrid", "Rufus Scrimgeour", "Salazar Slytherin",
        "Sally-Anne Perks", "Seamus Finnigan", "Nicholas De Mimsey Porpington", "Sirius Black", "Susan Bones", "The Bloody Barron", "Grey Lady", "Theodore Nott", "Tom Riddle", "Vernon Dursley",
        "Victoria Frobisher", "Viktor Krum", "Vincent Crabbe"};
}
