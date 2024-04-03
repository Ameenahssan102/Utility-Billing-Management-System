module javfx_assignment {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	requires junit;
	requires jdk.incubator.vector;
	
	
	opens application to javafx.graphics, javafx.fxml;
}
