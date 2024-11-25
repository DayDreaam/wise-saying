package com.ll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Study{
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(1, "Alice", 20, 'F'));
        people.add(new Person(2, "Bob", 25, 'M'));
        people.add(new Person(3, "David", 35, 'M'));

        // 문제 : 나이가 25세 이상인 사람들의 이름들을 출력
        people.stream()
                .filter(person -> person.getAge() >= 25)
                .map(Person::getName)
                .forEach(System.out::println);

        // 문제 : 남자 나이 총합 출력
        int sum = people.stream()
                .filter(person -> person.getGender()=='M')
                .mapToInt(Person::getAge)
                .sum();
        System.out.println("sum of age : " + sum);

        // 문제 : 남자 나이 평균 출력
        double avg = people.stream()
                .filter(person -> person.getGender()=='M')
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println("avg of age : " + avg);

        // 문제 : 나이가 25세 이상인 사람들의 이름들 출력 (한줄로)
        String manNames = people.stream()
                .filter(person -> person.getGender()=='M')
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("manNames : " + manNames);

        // 문제 : id가 2번인 사람
        // Optional 예제

        Optional<Person> opPerson =  people.stream()
                .filter(person -> person.getId() == 2)
                .findFirst(); // 필터 중 하나라도 나오면 바로 반환
                              // 없으면 empty 반환 (옵셔널로 받아줘야함)
        // Person p = opPerson.get(); 시스템 따운 (없으면 큰일나는 경우 사용)
        Person p = opPerson.orElse(null); // 옵셔널 안에 없으면 null 대입
        // 예외 처리 해줘야 함.. (없을떄)
        System.out.println("Id2 name :" + p.getName());


    }
}

class Person {
    private int id;
    private String name;
    private int age;
    private char gender;

    public Person(int id, String name, int age, char gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }
}