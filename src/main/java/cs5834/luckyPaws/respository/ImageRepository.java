package cs5834.luckyPaws.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cs5834.luckyPaws.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name); 
}
