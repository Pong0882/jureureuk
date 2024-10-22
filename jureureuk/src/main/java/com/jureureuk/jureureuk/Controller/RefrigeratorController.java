package com.jureureuk.jureureuk.controller;

import java.util.ArrayList; // ArrayList 임포트
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jureureuk.jureureuk.entity.Cocktail;
import com.jureureuk.jureureuk.entity.Ingredient;
import com.jureureuk.jureureuk.repository.CocktailRepository;
import com.jureureuk.jureureuk.service.CocktailService;
import com.jureureuk.jureureuk.service.IngredientService;
import com.jureureuk.jureureuk.service.UserIngredientService;

//import com.jureureuk.jureureuk.service.IngredientService;

@Controller
@RequestMapping("/refrigerator")
public class RefrigeratorController {

    private final IngredientService ingredientService;
    private final UserIngredientService userIngredientService;
    private final CocktailRepository cocktailRepository;
    private final CocktailService cocktailService;

    // 생성자 주입을 통해 의존성 주입
    @Autowired
    public RefrigeratorController(
            IngredientService ingredientService,
            UserIngredientService userIngredientService,
            CocktailRepository cocktailRepository,
            CocktailService cocktailService) {
        this.ingredientService = ingredientService;
        this.userIngredientService = userIngredientService;
        this.cocktailRepository = cocktailRepository;
        this.cocktailService = cocktailService;
    }

    @GetMapping("")
    public String refrigeratorMain(Model model, @AuthenticationPrincipal OAuth2User principal) {
        // 로그인된 사용자의 Google ID 가져오기
        String googleId = principal.getAttribute("email");

        // 사용자가 가진 재료로 만들 수 있는 칵테일 조회
        List<Cocktail> availableCocktails = cocktailRepository.findAvailableCocktailsByUserIngredients(googleId);

        // 빈 리스트가 아닌지 확인
        if (availableCocktails == null || availableCocktails.isEmpty()) {
            model.addAttribute("cocktails", new ArrayList<>()); // 빈 리스트 전달
        } else {
            model.addAttribute("cocktails", availableCocktails);
        }

        List<Map<String, String>> cocktails2 = cocktailService.getCocktailsWithOneMissingIngredient(googleId);
        model.addAttribute("cocktails2", cocktails2);

        return "refrigerator/main"; // refrigerator/main.html로 이동
    }

    @GetMapping("/prac")
    public String materialManagementprac(Model model) {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        model.addAttribute("ingredients", ingredients);
        return "refrigerator/prac"; // materialManagement.html 템플릿 파일로 이동
    }

    @GetMapping("/materialManagement")
    public String materialManagement(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        // 사용자가 보유한 재료 가져오기
        List<Ingredient> userIngredients = userIngredientService.findIngredientsByUser(email);
        System.out.println("사용자 이메일: " + email); // 디버깅용 출력
        System.out.println("사용자 보유 재료: " + userIngredients); // 디버깅용 출력
        // Model에 재료 목록 추가
        model.addAttribute("userIngredients", userIngredients);
        // List<Ingredient> ingredients = ingredientService.getAllIngredients();
        // model.addAttribute("ingredients", ingredients); // 이건 그냥 전체 가져오는거임
        return "refrigerator/materialManagement"; // materialManagement.html 템플릿 파일로 이동
    }

    @GetMapping("/MaterialInfo")
    public String showIngredientInfo(@RequestParam("id") Long id, Model model) {
        Ingredient ingredient = ingredientService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient ID: " + id));
        model.addAttribute("ingredient", ingredient);

        // 해당 재료로 만들 수 있는 칵테일 목록 가져오기
        List<Cocktail> cocktails = ingredientService.getCocktailsByIngredient(id);
        model.addAttribute("cocktails", cocktails);

        return "refrigerator/MaterialInfo";
    }

    @GetMapping("/shopping")
    public String shopping(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        // 로그인한 사용자의 이메일 가져오기

        // 사용자가 보유하지 않은 재료 목록 조회
        List<Ingredient> missingIngredients = userIngredientService.findMissingIngredientsByEmail(email);

        // 디버깅: 재료 목록 로그 출력
        System.out.println("조회된 재료 목록: " + missingIngredients);

        // Model에 추가
        model.addAttribute("missingIngredients", missingIngredients);

        return "refrigerator/shopping"; // 쇼핑 페이지로 이동
    }

    @PostMapping("/api/delete-ingredients")
    @ResponseBody
    public ResponseEntity<String> deleteIngredients(@RequestBody Map<String, List<Long>> request,
            OAuth2AuthenticationToken authentication) {
        try {
            String email = authentication.getPrincipal().getAttribute("email");
            List<Long> ingredientIds = request.get("ingredients");

            // 로그 추가
            System.out.println("사용자 이메일: " + email);
            System.out.println("삭제할 재료 ID 목록: " + ingredientIds);

            if (ingredientIds == null || ingredientIds.isEmpty()) {
                return ResponseEntity.badRequest().body("삭제할 재료 목록이 비어 있습니다.");
            }

            for (Long id : ingredientIds) {
                userIngredientService.deleteUserIngredient(email, id);
            }

            return ResponseEntity.ok("재료 삭제 완료");
        } catch (Exception e) {
            e.printStackTrace(); // 오류 메시지를 로그에 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        }
    }

    @PostMapping("/api/save-ingredients")
    @ResponseBody
    public ResponseEntity<String> saveIngredients(@RequestBody Map<String, List<Long>> request,
            OAuth2AuthenticationToken authentication) {
        try {
            String email = authentication.getPrincipal().getAttribute("email");
            List<Long> ingredientIds = request.get("ingredients");

            if (ingredientIds == null || ingredientIds.isEmpty()) {
                return ResponseEntity.badRequest().body("재료 목록이 비어 있습니다.");
            }

            ingredientIds.forEach(id -> {
                Ingredient ingredient = ingredientService.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 재료 ID: " + id));
                userIngredientService.saveUserIngredient(email, ingredient);
            });

            return ResponseEntity.ok("쇼핑이 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
}
