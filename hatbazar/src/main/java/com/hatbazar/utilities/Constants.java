package com.hatbazar.utilities;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/29/13
 * Time: 2:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static String MYSQL_USER          = "root";
    public static String MYSQL_PASSWORD      = "root";
    public static String MYSQL_DATABASE_NAME = "hatbazar";
    public static String DB_OUTPUT_PATH      ="C:"+ File.separator + "hatbazar" + File.separator + "hatbazar.sql";
    public static String PATH_TO_MYSQL = "C:\\Program Files\\MySQL\\MySQL Server 5.1\\bin\\";

    public static String DB_PATH = PATH_TO_MYSQL + "mysqldump -u" + MYSQL_USER + " -p" + MYSQL_PASSWORD + " --add-drop-database -B "+ MYSQL_DATABASE_NAME + " -r " + DB_OUTPUT_PATH;
}
