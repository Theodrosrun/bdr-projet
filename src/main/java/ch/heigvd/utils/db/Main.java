package ch.heigvd.utils.db;

import ch.heigvd.utils.controller.GeneralController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<HashMap<String, String> > table = new GeneralController().getCurrentAbosMuscu();
        System.out.println(Arrays.toString(table.toArray()));
    }
}
