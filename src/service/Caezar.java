package service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Caezar {

    //    char[] alphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',};
    ArrayList<String> alphabet = new ArrayList<>(List.of("a", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м",
            "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "щ", "ъ", "ы", "ь", "э", "ю", "я", ".", ",", "\"", ":",
            "-", "!", "?", " "));


    public ArrayList<String> encrypt(List<String> incomingFile, int key) {

        for (int i = 0; i < incomingFile.size(); i++) {
            for (int j = 0; j < alphabet.size(); j++) {
                if (incomingFile.get(i).equals(alphabet.get(j))) {
                    incomingFile.set(i, incomingFile.get(i + key));
                }
            }
        }

//        if (key > incomingFile.size()) {
//            key = key % incomingFile.size();
//        } else if (key < 0) {
//            key = (key % incomingFile.size()) + incomingFile.size();
//        }
//        for (int i = 0; i < incomingFile.size(); i++) {
//            if (i + key > incomingFile.size()) {
//                incomingFile.set(i, incomingFile.get(i - (incomingFile.size() - key)));
//            } else {
//                incomingFile.set(i, incomingFile.get((i + key) % incomingFile.size()));   //TODO не рабочий алгоритм!
//            }
//        }
        char[] ch = Character.toChars(Integer.parseInt(incomingFile.toString()));


        return null;
    }
    public void decryptFile () {

    }
}