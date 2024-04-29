package com.example.estudy.repository.practical;

import com.example.estudy.domain.lesson.content.practical.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestItemRepository extends JpaRepository<TestItem, Long> {

    List<TestItem> findAllByTestId(Long id);

    TestItem findByTestIdAndIsRightTrue(Long id);

}
