package com.ll;

class App {
    public void run() { // 여기서 Repository를 써도 되는지 모르겠음 (lastIndex)
        Repository repository = new Repository();
        System.out.println("== 명언 앱 ==");
        int lastId = repository.lastNumber();
        // lastId 받아오는 과정 필요 (Repository)

        Controller controller = new Controller();
        lastId = controller.run(lastId);

        // lastId 저장 (Repository)
        repository.saveFile("lastId.txt",String.valueOf(lastId));
    }
}
