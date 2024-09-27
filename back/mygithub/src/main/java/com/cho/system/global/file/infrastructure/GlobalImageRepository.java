package com.cho.system.global.file.infrastructure;

import com.cho.system.global.file.domain.model.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cho.system.global.file.domain.model.ImageFile;


@Repository
public interface GlobalImageRepository extends JpaRepository<ImageFile,Long> {

}
