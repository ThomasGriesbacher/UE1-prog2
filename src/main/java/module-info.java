module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires com.jfoenix;
    requires okhttp3;
    requires com.google.gson;

    requires java.sql;

    opens at.ac.fhcampuswien.fhmdb.models to com.google.gson;
    opens at.ac.fhcampuswien.fhmdb.database to ormlite.jdbc;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.models;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.controllers;
    opens at.ac.fhcampuswien.fhmdb.controllers to javafx.fxml;
}