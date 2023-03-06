package com.example.ToFindDuplicates.Controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToFindDuplicates.Services.AllFilesService;
import com.example.ToFindDuplicates.Services.DuplicateFileService;


@RestController
@RequestMapping("/get")
public class Controller {
	private final AllFilesService allfilesService;
	private final DuplicateFileService duplicatefileService;
	
	
	public  Controller(AllFilesService allfilesService,DuplicateFileService duplicatefileService)
	{
		this.allfilesService=allfilesService;
		this.duplicatefileService = duplicatefileService;
		
	}
	
	@GetMapping("/all-files")
	public List<String> get(@RequestParam String pathFolder)
	{
		List<File> files=allfilesService.getAllFiles(pathFolder);
		return files.stream().map(File::getName).collect(Collectors.toList());
	}
	@GetMapping("/duplicates")
	public List<List<String>> find(@RequestParam String pathFolder)
	{
		return duplicatefileService.findDuplicateFiles(pathFolder);
	}
}

	
	
	





