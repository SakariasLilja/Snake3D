module com.sakariaslilja {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sakariaslilja to javafx.fxml;
    exports com.sakariaslilja;
}
