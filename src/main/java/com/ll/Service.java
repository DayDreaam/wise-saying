package com.ll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {
    Repository repository = new Repository();

    String makeJson(WiseSaying wiseSaying){
        String json = "{\n" +
                "  \"id\": " + wiseSaying.id + ",\n" +
                "  \"content\": \"" + wiseSaying.content + "\",\n" +
                "  \"author\": \""+ wiseSaying.author + "\"\n" +
                "}";
        return json;
    }

    void createWiseSaying(WiseSaying wiseSaying){
        String json = makeJson(wiseSaying);
        String fileName = wiseSaying.id+".json";
        repository.saveFile(fileName,json);
    }

    boolean deleteWiseSaying(int id){
        String fileName = id + ".json";
        if(repository.isFile(fileName)){
            repository.deleteFile(fileName);
            return true;
        }
        else return false;
    }

    boolean updatable(int id){
        String fileName = id + ".json";
        if(repository.isFile(fileName)){
            return true;
        }
        else return false;
    }

    WiseSaying readWiseSaying(int id){
        String fileName = id + ".json";
        WiseSaying wiseSaying = repository.parseWiseSaying(repository.jsonToString(fileName));
        return wiseSaying;
    }

    String printAllDesc(){
        List<String> list = new ArrayList<>();
        String[] files = repository.jsonsInDirectory();
        for(String file : files){
            String json = repository.jsonToString(file);
            WiseSaying wiseSaying = repository.parseWiseSaying(json);
            String s = wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content;
            list.add(s);
        }
        Collections.reverse(list);
        String result = "";
        for(String str : list){
            result += str;
            result += "\n";
        }
        return result;
    }
}
