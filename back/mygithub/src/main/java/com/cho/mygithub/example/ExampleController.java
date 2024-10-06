package com.cho.mygithub.example;


import com.cho.system.global.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/example", headers = "X-APIVERSION=1.0.0")
@RequiredArgsConstructor
public class ExampleController {
        private final ExampleService exampleService;

    @GetMapping(value = "",headers = "X-APIVERSION=2.0.0",produces = "application/json;charset=UTF-8")

    public ApiResponse<?>  getExampleInfo() {
        return ApiResponse.success("This is version 1 ");
    }

    @GetMapping(value = "",produces = "application/json;charset=UTF-8")

    public ApiResponse<?>  getExampleInfoAll() {
        return ApiResponse.success("This is version 2");
    }


}
