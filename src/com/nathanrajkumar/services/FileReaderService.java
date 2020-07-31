package com.nathanrajkumar.services;

import java.util.List;

import com.nathanrajkumar.model.TeslaSale;

public interface FileReaderService {

	List<TeslaSale> readFile(String fileName);
}
