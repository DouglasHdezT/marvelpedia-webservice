package me.douglashdezt.simanmarvelpediaws.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ErrorMapper {
    public static Map<String, List<String>> map(List<FieldError> errors) {
        Map<String, List<String>> result = new HashMap<>();
        errors.forEach(error -> {
            List<String> state = result.getOrDefault(error.getField(), new ArrayList<>());
            state.add(error.getDefaultMessage());
            result.put(error.getField(), state);
        });
        return result;
    }
}
