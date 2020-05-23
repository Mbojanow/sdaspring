package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Toy;
import pl.sdacademy.wiosnademo.model.ToyDto;
import pl.sdacademy.wiosnademo.model.ToyMapper;
import pl.sdacademy.wiosnademo.repositories.ToyRepository;

@Service
@Transactional
public class ToyService {

  private final ToyMapper toyMapper;
  private final ToyRepository toyRepository;

  public ToyService(final ToyMapper toyMapper, final ToyRepository toyRepository) {
    this.toyMapper = toyMapper;
    this.toyRepository = toyRepository;
  }

  public ToyDto create(ToyDto toyDto) {
    final Toy toy = toyMapper.toyDtoToToy(toyDto);
    final Toy savedToy = toyRepository.save(toy);
    return toyMapper.toyToToyDto(savedToy);
  }
}
