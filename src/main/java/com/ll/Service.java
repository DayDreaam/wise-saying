package com.ll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ll.Repository.DataJson;

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
        String fileName = repository.makeJsonName(wiseSaying.id);
        repository.saveFile(fileName,json);
    }

    boolean deleteWiseSaying(int id){
        String fileName = repository.makeJsonName(id);
        if(repository.isFile(fileName)){
            repository.deleteFile(fileName);
            return true;
        }
        else return false;
    }

    boolean updatable(int id){
        String fileName = repository.makeJsonName(id);
        if(repository.isFile(fileName)){
            return true;
        }
        else return false;
    }

    WiseSaying readWiseSaying(int id){
        String fileName = repository.makeJsonName(id);
        WiseSaying wiseSaying = repository.parseWiseSaying(repository.jsonToString(fileName));
        return wiseSaying;
    }

    List<String> jsonDesc(){
        List<String> list = new ArrayList<>();
        String[] files = repository.jsonsInDirectory();
        for(String file : files){
            if(file == DataJson){
                continue;
            }
            String json = repository.jsonToString(file);
            WiseSaying wiseSaying = repository.parseWiseSaying(json);
            String s = wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content;
            list.add(s);
        }
        Collections.reverse(list);
        return list;
    }

    void buildData(){
        List<String> list = new ArrayList<>();
        String[] files = repository.jsonsInDirectory();
        String data = "[\n";
        for(String file : files){
            if(file == DataJson){
            continue;
            }
            if(data != "[\n"){
                data += ",\n";
            }
            String json = repository.jsonToString(file);
            WiseSaying wiseSaying = repository.parseWiseSaying(json);
            data += "  {\n" +
                    "    \"id\": " + wiseSaying.id + ",\n" +
                    "    \"content\": \"" + wiseSaying.content + "\",\n" +
                    "    \"author\": \""+ wiseSaying.author + "\"\n" +
                    "  }";
        }
        data += "\n]";
        repository.saveFile(DataJson,data);
    }
}
