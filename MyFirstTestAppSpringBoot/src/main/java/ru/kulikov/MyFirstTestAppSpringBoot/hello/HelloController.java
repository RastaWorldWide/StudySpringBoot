package ru.kulikov.MyFirstTestAppSpringBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    ArrayList<String> arrayList;
    HashMap<Integer, String> hashMap;
    int number = 0;
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/update-array")
    public void updateArrayList(@RequestParam(value = "argument") String argument) {
        if (arrayList == null) {
            arrayList = new ArrayList<String>();
        }
        arrayList.add(argument);
    }
    @GetMapping("/show-array")
    public ArrayList<String> showArrayList() {
        return arrayList;
    }
    @GetMapping("/update-map")
    public void updateHashMap(@RequestParam(value = "argument") String argument) {
        if (hashMap == null) {
            hashMap = new HashMap<Integer, String>();
        }
        hashMap.put(number, argument);
        number++;
    }
    @GetMapping("/show-map")
    public HashMap<Integer, String> showHashMap() {
        return hashMap;
    }
    @GetMapping("/show-all-lenght")
    public int showAllLength() {
        return arrayList.size() + hashMap.size();
    }
}
