package com.example.ToFindDuplicates.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ToFindDuplicates.Services.DuplicateFileService;

@Service
public class DuplicateFileServiceImpl implements DuplicateFileService {

	@Override
	public List<List<String>> findDuplicateFiles(String pathFolder) {
    Map<String,List<File>> fileMap=new HashMap<>();
		
		//get all the files in the folder
		File folder=new File(pathFolder);
		File[] files=folder.listFiles();
		
		//Read the contents of each file and put in a map
		for(File file:files)
		{
			if(file.isFile())
			{
				try {
					Path path=Paths.get(file.getAbsolutePath());
					byte[] bytes=Files.readAllBytes(path);
					String content=new String(bytes,Charset.defaultCharset());
					if(fileMap.containsKey(content)) {
						fileMap.get(content).add(file);
					}
					else {
						List<File> fileList=new ArrayList<>();
						fileList.add(file);
						fileMap.put(content,fileList);
					}
				}catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		List<List<String>> duplicateFiles=fileMap.values().stream().filter(filesList->filesList.size()>1).map(filesList->filesList.stream().map(File::getName)
				.collect(Collectors.toList())).collect(Collectors.toList());
		
		return duplicateFiles;
	}
	}


