package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Controller {
    public int run(int lastId){
        Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            }

            else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                ++lastId;
                WiseSaying wiseSaying = new WiseSaying(lastId, content, author);

                service.createWiseSaying(wiseSaying);
                System.out.println("%d번 명언이 등록되었습니다.".formatted(lastId));
            }

            else if (cmd.substring(0,2).equals("삭제")){
                int id = Integer.parseInt(cmd.split("=")[1]);

                if(service.deleteWiseSaying(id)){
                    System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
                }else{
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
                }
            }

            else if (cmd.substring(0,2).equals("수정")){
                int id = Integer.parseInt(cmd.split("=")[1]);

                if(service.updatable(id)){
                    WiseSaying wiseSaying = service.readWiseSaying(id);
                    System.out.print("명언(기존) : %s\n명언 : ".formatted(wiseSaying.content));
                    String content = scanner.nextLine();
                    System.out.print("작가(기존) : %s\n작가 : ".formatted(wiseSaying.author));
                    String author = scanner.nextLine();
                    wiseSaying = new WiseSaying(id, content, author);
                    service.createWiseSaying(wiseSaying);
                    System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
                }else{
                    System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
                }
            }

            else if (cmd.equals("목록")){
                System.out.print("""
                        번호 / 작가 / 명언
                        ----------------------
                        """);
                List<String> list = service.jsonDesc();
                for(String str : list){
                    System.out.println(str);
                }
            }

            else if (cmd.equals("빌드")){
                service.buildData();
                System.out.println("data.json 파일의 내용이 갱신되었습니다.");
            }
        }
        scanner.close();
        return lastId;
    }
}
