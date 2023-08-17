package net.canbot.canmod.util.misc;

import java.util.List;
import java.util.stream.Collectors;

public class Strings {

    public static String fromStringList(List<String> list) {
        String result = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        return result;
    }
}
