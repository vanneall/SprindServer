package ru.point.service.implementations;

import org.springframework.stereotype.Service;
import ru.point.entity.table.Shop;
import ru.point.repository.interfaces.ShopRepository;
import ru.point.service.interfaces.ShopService;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public List<Shop> getAllShop() {
        return shopRepository.getAllShop();
    }

}
