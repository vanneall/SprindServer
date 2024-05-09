package ru.point.entity.mapper;

import org.springframework.stereotype.Component;
import ru.point.entity.dto.CharacteristicDto;
import ru.point.entity.table.Characteristic;

import java.util.function.Function;

@Component
public class CharacteristicToCharacteristicDtoMapper implements Function<Characteristic, CharacteristicDto> {
    @Override
    public CharacteristicDto apply(Characteristic characteristic) {
        return new CharacteristicDto(
            characteristic.getName(),
            characteristic.getDescriptions()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue() + ";")
                .reduce((s, s2) -> s + s2).get()
        );
    }
}
