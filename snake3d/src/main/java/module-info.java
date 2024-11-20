module com.sakariaslilja {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sakariaslilja to javafx.fxml;
    opens com.sakariaslilja.controllers to javafx.fxml;
    exports com.sakariaslilja;
    exports com.sakariaslilja.controllers;
}
