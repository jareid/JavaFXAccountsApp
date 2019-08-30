package com.jreid.settings;

public class Settings {
    public static String DATABASE_FLAT_FILE = System.getProperty("user.dir").replace("\\", "/") + "/accounts.db";
    public static String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS accounts (\n"
                                             + " id integer PRIMARY KEY,\n"
                                             + " description varchar(255),\n"
                                             + " amount real,\n"
                                             + " date timestamp\n"
                                             + ");";
    public static String INSERT_DATA_SQL = "INSERT INTO accounts(description, amount, date) VALUES(?,?,?)";
    public static String SELECT_DATA_SQL = "SELECT * FROM accounts";
}
