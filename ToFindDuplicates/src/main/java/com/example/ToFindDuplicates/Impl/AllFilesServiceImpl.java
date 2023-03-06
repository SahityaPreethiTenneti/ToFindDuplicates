package com.example.ToFindDuplicates.Impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.ToFindDuplicates.Services.AllFilesService;

@Service
public class AllFilesServiceImpl implements AllFilesService {

	@Override
	public List<File> getAllFiles(String pathFolder) {
		File folder=new File(pathFolder);
		
		File[] files = folder.listFiles();
		
		return Arrays.asList(files);
	}



}
