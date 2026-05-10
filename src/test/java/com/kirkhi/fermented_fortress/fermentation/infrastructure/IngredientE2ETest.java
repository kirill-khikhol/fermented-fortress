package com.kirkhi.fermented_fortress.fermentation.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.kirkhi.fermented_fortress.fermentation.application.dto.IngredientDto;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.IngredientType;
import com.kirkhi.fermented_fortress.fermentation.domain.model.enums.Unit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@AutoConfigureRestTestClient
class IngredientE2ETest {

	@Autowired
	private RestTestClient restTestClient;

	@Autowired
	private JpaIngredientRepository jpaRepo;

	@BeforeEach
	void setup() {
		jpaRepo.deleteAll();
	}

	@Test
	void createAndGetById() {
		IngredientDto dto = new IngredientDto();
		dto.setName("Cabbage");
		dto.setDefaultUnit(Unit.PIECE.name());
		dto.setIngredientType(IngredientType.SOLID.name());
		dto.setNotes("for kimchi");

		restTestClient.post().uri("/api/ingredients").body(dto).exchange().expectStatus().isOk()
				.expectBody(IngredientDto.class).value(created -> {
					assertNotNull(created);
					assertEquals("Cabbage", created.getName());

					// ensure persisted
					jpaRepo.findAll().stream().findFirst().ifPresent(entity -> {
						assertEquals(created.getId(), entity.getId());
						assertEquals("Cabbage", entity.getName());
					});
				});
	}

	@Test
	void createDuplicatedAndFail() {
		IngredientDto dto = new IngredientDto();
		dto.setName("Cabbage");
		dto.setDefaultUnit(Unit.PIECE.name());
		dto.setIngredientType(IngredientType.SOLID.name());
		dto.setNotes("for kimchi");

		// first create should succeed
		restTestClient.post().uri("/api/ingredients").body(dto).exchange().expectStatus().isOk()
				.expectBody(IngredientDto.class).value(created -> {
					assertNotNull(created);
					assertEquals("Cabbage", created.getName());
				});

		// second create with same name should fail (unique constraint -> server error
		// handled by GlobalExceptionHandler)
		restTestClient.post().uri("/api/ingredients").body(dto).exchange().expectStatus().is5xxServerError();

		// ensure only one persisted
		long count = jpaRepo.findAll().size();
		assertEquals(1, count);
	}

	@Test
	void updateAndDelete() {
		// left intentionally blank - split into separate tests below
	}

	@Test
	void updateIngredient() {
		// create
		IngredientDto dto = new IngredientDto();
		dto.setName("Garlic");
		dto.setDefaultUnit(Unit.CLOVE.name());
		dto.setIngredientType(IngredientType.SOLID.name());
		dto.setNotes("fresh");

		IngredientDto created = restTestClient.post().uri("/api/ingredients").body(dto).exchange().expectStatus().isOk()
				.expectBody(IngredientDto.class).returnResult().getResponseBody();
		assertNotNull(created);

		Long id = created.getId();

		// update
		IngredientDto update = new IngredientDto();
		update.setName("Roasted Garlic");
		update.setDefaultUnit(Unit.CLOVE.name());
		update.setIngredientType(IngredientType.SOLID.name());
		update.setNotes("roasted");

		restTestClient.put().uri("/api/ingredients/{id}", id).body(update).exchange().expectStatus().isOk()
				.expectBody(IngredientDto.class).value(updated -> {
					assertEquals("Roasted Garlic", updated.getName());
					assertNotEquals(created.getName(), updated.getName());
				});
	}

	@Test
	void deleteIngredient() {
		// create
		IngredientDto dto = new IngredientDto();
		dto.setName("Onion");
		dto.setDefaultUnit(Unit.PIECE.name());
		dto.setIngredientType(IngredientType.SOLID.name());
		dto.setNotes("yellow");

		IngredientDto created = restTestClient.post().uri("/api/ingredients").body(dto).exchange().expectStatus().isOk()
				.expectBody(IngredientDto.class).returnResult().getResponseBody();
		assertNotNull(created);

		Long id = created.getId();

		// delete
		restTestClient.delete().uri("/api/ingredients/{id}", id).exchange().expectStatus().isOk();

		// ensure deleted in repository
		long count = jpaRepo.findAll().stream().filter(e -> e.getId().equals(id)).count();
		assertEquals(0, count);
	}

	@Test
	void findById() {
		IngredientDto dto = new IngredientDto();
		dto.setName("Pear");
		dto.setDefaultUnit(Unit.PIECE.name());
		dto.setIngredientType(IngredientType.SOLID.name());
		dto.setNotes("sweet");

		IngredientDto created = restTestClient.post().uri("/api/ingredients").body(dto).exchange().expectStatus().isOk()
				.expectBody(IngredientDto.class).returnResult().getResponseBody();
		assertNotNull(created);

		Long id = created.getId();

		restTestClient.get().uri("/api/ingredients/{id}", id).exchange().expectStatus().isOk()
				.expectBody(IngredientDto.class).value(found -> {
					assertNotNull(found);
					assertEquals("Pear", found.getName());
				});
	}

	@Test
	void getAllIngredients() {
		// create two ingredients
		IngredientDto a = new IngredientDto();
		a.setName("Tomato");
		a.setDefaultUnit(Unit.PIECE.name());
		a.setIngredientType(IngredientType.SOLID.name());
		a.setNotes("red");

		IngredientDto b = new IngredientDto();
		b.setName("Potato");
		b.setDefaultUnit(Unit.PIECE.name());
		b.setIngredientType(IngredientType.SOLID.name());
		b.setNotes("starchy");

		restTestClient.post().uri("/api/ingredients").body(a).exchange().expectStatus().isOk();
		restTestClient.post().uri("/api/ingredients").body(b).exchange().expectStatus().isOk();

		restTestClient.get().uri("/api/ingredients").exchange().expectStatus().isOk().expectBody(IngredientDto[].class)
				.value(arr -> {
					List<IngredientDto> list = Arrays.asList(arr);
					assertEquals(2, list.size());
					assertTrue(list.stream().anyMatch(d -> "Tomato".equals(d.getName())));
					assertTrue(list.stream().anyMatch(d -> "Potato".equals(d.getName())));
				});
	}
}
