package ru.point.service.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.point.entity.dto.FeedProductDto;
import ru.point.service.interfaces.ProductService;
import ru.point.service.interfaces.ShopService;

import java.util.List;

@AllArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {

    private final ProductService productService;

    @Override
    public List<FeedProductDto> getProductsByShop(int offset, int limit, long shopId, String username) {
        return productService.getProductsByShop(offset, limit, shopId, username);
    }
}
