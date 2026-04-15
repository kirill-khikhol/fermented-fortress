package com.kirkhi.fermented_fortress.fermentation.infrastructure;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirkhi.fermented_fortress.fermentation.application.RecipeCommandService;
import com.kirkhi.fermented_fortress.fermentation.application.RecipeQueryService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
	Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
//	private final RecipeCommandService recipeCommandService;
//	private final RecipeQueryService recipeQueryService;
//
//	@Autowired
//	public RecipeController(RecipeCommandService recipeCommandService, RecipeQueryService recipeQueryService) {
//		super();
//		this.recipeCommandService = recipeCommandService;
//		this.recipeQueryService = recipeQueryService;
//	}


	@GetMapping
	public ResponseEntity<List<RecipeDto>> getAll(){
		logger.info("Called getAll");
		return ResponseEntity.ok(null);
	}

}
