package com.masai.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.FileData;

public interface FileDataDao extends JpaRepository<FileData, Long> {

	  public FileData findByName(String name);
}
