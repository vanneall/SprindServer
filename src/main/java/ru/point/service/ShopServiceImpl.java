package ru.point.service;

import org.springframework.stereotype.Service;
import ru.point.entity.Shop;
import ru.point.repository.interfaces.ShopRepository;

import java.util.List;

@Service
public class ShopServiceImpl {

    private final ShopRepository shopRepository;

    ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAllShop() {
        return shopRepository.getAllShop();
    }

}
