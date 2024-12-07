module com.sakariaslilja {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive com.google.gson;
    requires transitive javafx.graphics;

    opens com.sakariaslilja to javafx.fxml, gson;
    opens com.sakariaslilja.controllers to javafx.fxml;
    opens com.sakariaslilja.services to javafx.fxml, gson;
    opens com.sakariaslilja.models to javafx.fxml;
    opens com.sakariaslilja.entities to javafx.fxml;
    opens com.sakariaslilja.datastructures to javafx.fxml, gson, javafx.graphics;
    
    exports com.sakariaslilja;
    exports com.sakariaslilja.controllers;
    exports com.sakariaslilja.services;
    exports com.sakariaslilja.models;
    exports com.sakariaslilja.entities;
    exports com.sakariaslilja.datastructures;
}
