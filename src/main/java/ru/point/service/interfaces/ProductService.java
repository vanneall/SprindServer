package ru.point.service.interfaces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.entity.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<FeedProductDto> getProducts(Integer limit, Integer offset);

    List<FeedProductDto> getProductsByName(Integer limit, Integer offset, String name);

    ProductDto getProductById(Long id);

}
