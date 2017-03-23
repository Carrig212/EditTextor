package editor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;

public class EditorController {
    @FXML
    private TextArea textArea;
    private EditorModel editorModel;
    private TextFile currentTextFile;

    public EditorController(EditorModel editorModel) {
        this.editorModel = editorModel;
    }

    @FXML
    private void onOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            IOResult<TextFile> ioResult = editorModel.open(file.toPath());

            if (ioResult.isOkay() && ioResult.hasData()) {
                currentTextFile = ioResult.getData();
                textArea.clear();
                currentTextFile.getContent().forEach(line -> textArea.appendText(line + "\n"));
            } else {
                System.out.println("Failed");
            }
        }
    }

    @FXML
    private void onSave() {
        TextFile textFile = new TextFile(currentTextFile.getFile(), Arrays.asList(textArea.getText().split("\n")));
        editorModel.save(textFile);
    }

    @FXML
    private void onClose() {
        editorModel.close();
    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("This is my very simple text editor 'EditTextor'!");
        alert.show();
    }
}
