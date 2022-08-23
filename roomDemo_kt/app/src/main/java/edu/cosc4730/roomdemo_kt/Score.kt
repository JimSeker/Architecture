package edu.cosc4730.roomdemo_kt

import edu.cosc4730.roomdemo_kt.Score
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import android.provider.BaseColumns
import androidx.room.Entity
import androidx.room.Ignore

/**
 * Score  Plain Old Java Object  This needs to provide getters and setters for everything plus the
 * constructors for the object.   The table in the database is not specified, so it will be score (the Class name)
 */
@Entity(tableName = Score.TABLE_NAME)
class Score {
    /**
     * getters and setters for each value.
     */
    /**
     * The unique ID each score data pair.
     */
    @JvmField
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    var id: Long = 0

    /**
     * The name of the person.
     */
    @JvmField
    @ColumnInfo(name = COLUMN_NAME)
    var name: String = ""

    /**
     * The person's score.
     */
    @JvmField
    @ColumnInfo(name = COLUMN_SCORE)
    var score = 0
    fun setId(id: Int) {
        this.id = id.toLong()
    }

    // blank constructor
    @Ignore
    constructor() {
    }

    // constructor for the 2 data points.
    constructor(name: String, score: Int) {
        this.name = name
        this.score = score
    }

    companion object {
        /**
         * The name of the Score table.
         */
        const val TABLE_NAME = "scores"

        /**
         * The name of the ID column.
         */
        const val COLUMN_ID = BaseColumns._ID

        /**
         * The name of the name column.
         */
        const val COLUMN_NAME = "name"

        /**
         * The name of the score column.
         */
        const val COLUMN_SCORE = "score"

        //some names for the dummy data.
        val names = arrayOf(
            "Adrian Pucey",
            "Alastor Mad Eye Moody",
            "Albus Dumbledore",
            "Alguff Awful",
            "Alice Longbottom",
            "Alicia Spinnet",
            "Ambrosius Flume",
            "Amelia Susan Bones",
            "Amos Diggory",
            "Andrew Kirke",
            "Andromeda Blackv",
            "Andros the Invincible",
            "Angelina Johnson",
            "Arabella Figg",
            "Argus Filch",
            "Arthur Weasley",
            "Augusta Longbottom",
            "Barny the Fruitbat",
            "Bartemius Crouch Jr.",
            "Bartemius Crouch Senior",
            "Belatrix Black",
            "Bellatrix Lestrange",
            "Bertha Jorkins",
            "Bertie Bott",
            "Bill Weasly",
            "Birtha Jorkins",
            "Blaise Zabini",
            "Bloody Barron",
            "Bob Ogden",
            "Cassius  Warrington",
            "Cedric Diggory",
            "Charlie Weasley",
            "Cho Chang",
            "Ciceron Harkis",
            "Colin Creevy",
            "Cormac McLaggen",
            "Cornelious Fudge",
            "Crookshanks",
            "Daphne Greengrass",
            "Dean Thomas",
            "Dedulus Diggle",
            "Dennis Creevey",
            "Dobby House Elf",
            "Dolores Umbrige",
            "Draco Malfoy",
            "Duddly Dersly",
            "Eddie Carmichael",
            "Edgar Bones",
            "Egor Karkeroff",
            "Eleanor Branstone",
            "Eloise Midgeon",
            "Emma Dobbs",
            "Emmaline Vance",
            "Ernie Mcmillan",
            "Ernie Prang",
            "Fat Fryer",
            "Fat Lady",
            "Fenrir Greyback",
            "Filius Flitwick",
            "Finius Nigelus",
            "Fleur Delacour",
            "Florean Fortesque",
            "Frank Bryce",
            "Fred Weasley",
            "Gabrielle Delacour",
            "George Weasley",
            "Gildaroy Lockheart",
            "Ginny Weasley",
            "Godric Gryffindor",
            "Graham Pritchard",
            "Gregory Goyle",
            "Gwenog Jones",
            "Hannah Abbott",
            "Harry Potter",
            "Hecter Dagworth Granger",
            "Helga Hufflepuff",
            "Herbert Chorley",
            "Hermione Jane Granger",
            "Herold Dingle",
            "Humphrey Belcher",
            "Igor Karkaroff",
            "James Potter",
            "Justin Finch Flechly",
            "Katie Bell",
            "Kingsley Shacklebolt",
            "Laura Madley",
            "Lavender Brown",
            "Lee Jordan",
            "Lily Potter",
            "Lord Voldemort",
            "Lucius Malfoy",
            "Ludovic Ludo Bagman",
            "Luna Lovegood",
            "Madam Hooch",
            "Madam Malkin",
            "Madam Pince",
            "Madam Pomfrey",
            "Madam Rosmerta",
            "Maddam Maxime",
            "Maddam Pomfrey",
            "Maddam Rosemerter",
            "Malcolm Baddock",
            "Marcus Belby",
            "Marcus Flint",
            "Marge Dursley",
            "Marietta Edgecome",
            "Mark Evans",
            "Marvalo Guant",
            "Merope Gaunt",
            "Micheal Corner",
            "Millicent Bagnold",
            "Millicent Bulstrode",
            "Minerva McGonagall",
            "Moaning Myrtle",
            "Molly Weasley",
            "Montague",
            "Morfin Gaunt",
            "Mundungus Fletcher",
            "Narcissa Malfoy",
            "Neville Longbottom",
            "Nicholas Flamel",
            "Nimphodorah Tonks",
            "Theodore Nott",
            "Nymphadora Tonks",
            "Olivander",
            "Oliver Wood",
            "Olympe Maxime",
            "Orla Quirke",
            "Otto Bagman",
            "Owen Cauldwell",
            "Padma Patil",
            "Pansy Parkinson",
            "Parvatie Patil",
            "Penelope Clearwater",
            "Percy Weasly",
            "Pertunia Dursly",
            "Peter Petigrue",
            "Petunia Dursley",
            "Piers Polkis",
            "Pigwigeon",
            "Severus Snape",
            "Remus Lupin",
            "Rits Skeeter",
            "Roger Davies",
            "Romilda Vane",
            "Ron Weasley",
            "Rose Zeller",
            "Rowena Ravenclaw",
            "Rubeus Hagrid",
            "Rufus Scrimgeour",
            "Salazar Slytherin",
            "Sally-Anne Perks",
            "Seamus Finnigan",
            "Nicholas De Mimsey Porpington",
            "Sirius Black",
            "Susan Bones",
            "The Bloody Barron",
            "Grey Lady",
            "Theodore Nott",
            "Tom Riddle",
            "Vernon Dursley",
            "Victoria Frobisher",
            "Viktor Krum",
            "Vincent Crabbe"
        )
    }
}