package ru.point.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.table.complex.ComplexFeedDto;
import ru.point.entity.table.complex.FeedDto;
import ru.point.service.interfaces.ProductService;
import ru.point.service.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sprind/feed")
class FeedProductRestController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/info")
    public FeedDto getMainInfo(Principal principal) {
        String username = principal != null ? principal.getName() : null;
        var address = username != null ? userService.getUserInfoByUsername(username).address() : null;

        return new FeedDto(address);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FeedProductDto> getProductsEndpoint(
        @RequestParam(required = false, name = "name") String productName,
        @RequestParam(name = "offset") int offset,
        @RequestParam(name = "limit") int limit,
        Principal principal
    ) {
        String username = principal != null ? principal.getName() : null;
        return productName == null ?
            productService.getProducts(offset, limit, username) :
            productService.getProductsByName(productName, offset, limit, username);
    }
}
