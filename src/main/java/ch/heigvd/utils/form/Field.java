package ch.heigvd.utils.form;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Field {
    private final String labelName;
    private final String inputName;
    private final String inputType;
    private List<String> options = null;
    public static List<Field> ALL_FIELDS = new ArrayList<>();
    public static List<String> ALL_LABELS = new ArrayList<>();
    public static List<String> ALL_INPUT_NAMES = new ArrayList<>();
    public static List<String> ALL_INPUT_TYPES = new ArrayList<>();

    public Field(String labelName, String inputName, String inputType, List<String> options) {
        if(!inputType.equals("select")) {
            throw new RuntimeException("Le type de champ doit être 'select'");
        }
        this.labelName = labelName;
        this.inputName = inputName;
        this.inputType = inputType;
        this.options = List.copyOf(options);
        ALL_LABELS.add(labelName);
        ALL_INPUT_NAMES.add(inputName);
        ALL_INPUT_TYPES.add(inputType);
    }

    public Field(String labelName, String inputName, String inputType) {
        this.labelName = labelName;
        this.inputName = inputName;
        this.inputType = inputType;
        this.options = null;
        ALL_LABELS.add(labelName);
        ALL_INPUT_NAMES.add(inputName);
        ALL_INPUT_TYPES.add(inputType);
    }

    public Field getField(String inputName) {
        if (!ALL_INPUT_NAMES.contains(inputName)) {
            throw new RuntimeException("Le champ " + inputName + " n'existe pas");
        }
        int index = ALL_INPUT_NAMES.indexOf(inputName);
        return ALL_FIELDS.get(index);
    }

    public void setOptions(List<String> options) {
        if (!inputType.equals("select")) {
            return;
        }
        this.options = List.copyOf(options);
    }
}
