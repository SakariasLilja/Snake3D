module com.sakariaslilja {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.sakariaslilja to javafx.fxml, gson;
    opens com.sakariaslilja.controllers to javafx.fxml;
    opens com.sakariaslilja.services to javafx.fxml, gson;
    opens com.sakariaslilja.models to javafx.fxml;
    opens com.sakariaslilja.entities to javafx.fxml;
    
    exports com.sakariaslilja;
    exports com.sakariaslilja.controllers;
    exports com.sakariaslilja.services;
    exports com.sakariaslilja.models;
    exports com.sakariaslilja.entities;
}
