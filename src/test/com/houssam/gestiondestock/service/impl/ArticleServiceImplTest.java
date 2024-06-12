package com.houssam.gestiondestock.service.impl;

import com.houssam.gestiondestock.dto.ArticleDto;
import com.houssam.gestiondestock.mapper.ArticleMapper;
import com.houssam.gestiondestock.model.Article;
import com.houssam.gestiondestock.repository.ArticleRepository;
import com.houssam.gestiondestock.service.ArticleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@Tag("CRUDTEST")
@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Autowired
    ArticleService articleService;
    @Mock
    ArticleRepository articleRepositoryMock;
    @Mock
    ArticleMapper articleMapperMock;

    @InjectMocks
    ArticleServiceImpl articleServiceImpl;
    private static ArticleDto articleDto;

    private static ArticleDto articleDto2;
    private static Instant startedAt;
    private static Instant endedAt;
    @BeforeAll
    public static void init(){
        startedAt = Instant.now();
    }

    @BeforeEach
    public void beforeEach(){
        articleDto2 = ArticleDto.builder()
                .id(1).dateCreation(Instant.now()).
                codeArticle("code1").
                prixunitaireHT(BigDecimal.valueOf(23)).
                tauxTva(BigDecimal.valueOf(20)).prixunitaireTtc(BigDecimal.valueOf(34)).build();
    }

    @AfterEach
    public void afterEach(){
        articleDto2 = null;
    }

    public static Stream<Arguments> generateData(){
        return Stream.of(
                Arguments.of("ATest1",BigDecimal.valueOf(34),BigDecimal.valueOf(20),BigDecimal.valueOf(66)),
                Arguments.of("ATest2",BigDecimal.valueOf(560),BigDecimal.valueOf(20),BigDecimal.valueOf(840)),
                Arguments.of("ATest3",BigDecimal.valueOf(130),BigDecimal.valueOf(20),BigDecimal.valueOf(170))
        );
    }

    @AfterAll
    static public void afterAll(){
        endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durreé des tests article services : {0} milli secondes",duration));
    }


    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("Enregistrer un article et verifier que l'id et la date de creation sont generées.")
    @Timeout(1)
    void save(String code, BigDecimal prixHt , BigDecimal tauxTva , BigDecimal prixTtc ) {
        //Arrange
        articleDto = ArticleDto.builder()
                .id(1).dateCreation(Instant.now()).
                codeArticle(code).
                prixunitaireHT(prixHt).
                tauxTva(tauxTva).prixunitaireTtc(prixTtc).build();
        Article article = new Article();
        when(articleRepositoryMock.save(any())).thenReturn(article);
        when(articleMapperMock.articleToArticleDto(article)).thenReturn(articleDto);
        //Act
        Article articleMock = articleRepositoryMock.save(any());
        ArticleDto articleDtoResult = articleMapperMock.articleToArticleDto(articleMock);
        ArticleDto articleResult = articleService.save(articleDtoResult);

        //Assert

        //Assertions Junit5
        Assertions.assertNotNull(articleResult);
        Assertions.assertNotNull(articleResult.getId());
        Assertions.assertNotNull(articleResult.getDateCreation());
        Assertions.assertEquals(articleResult.getCodeArticle(),articleDto.getCodeArticle());
        Assertions.assertEquals(articleResult.getPrixunitaireHT(),articleDto.getPrixunitaireHT());
        Assertions.assertEquals(articleResult.getPrixunitaireTtc(),articleDto.getPrixunitaireTtc());
        Assertions.assertEquals(articleResult.getTauxTva(),articleDto.getTauxTva());

        //Assertion AssertJ
        verify(articleRepositoryMock , times(1)).save(any());
        verify(articleMapperMock).articleToArticleDto(any());
        verify(articleMapperMock , never()).ArticleDtoToArticle(any());
        assertThat(articleResult).isNotNull();
        assertThat(articleResult.getId()).isNotNull();
        assertThat(articleResult.getDateCreation()).isNotNull();
        assertThat(articleResult.getCodeArticle()).isEqualTo(articleDto.getCodeArticle());
        assertThat(articleResult.getDateModification()).isNull();
    }

    @ParameterizedTest
    @CsvSource({"c1,23,20,38" , "c2,66,20,88"})
    @DisplayName("Enregistrer Article et tester @CsvSource")
    void save2(String code, BigDecimal prixHt , BigDecimal tauxTva , BigDecimal prixTtc ) {
        //Arrange
        articleDto = ArticleDto.builder()
                .id(1).dateCreation(Instant.now()).
                codeArticle(code).
                prixunitaireHT(prixHt).
                tauxTva(tauxTva).prixunitaireTtc(prixTtc).build();
        Article article = new Article();
        when(articleRepositoryMock.save(any())).thenReturn(article);
        when(articleMapperMock.articleToArticleDto(article)).thenReturn(articleDto);
        //Act
        Article articleMock = articleRepositoryMock.save(any());
        ArticleDto articleDtoResult = articleMapperMock.articleToArticleDto(articleMock);
        ArticleDto articleResult = articleService.save(articleDtoResult);

        //Assert

        //Assertions Junit5
        Assertions.assertNotNull(articleResult);
        Assertions.assertNotNull(articleResult.getId());
        Assertions.assertNotNull(articleResult.getDateCreation());
        Assertions.assertEquals(articleResult.getCodeArticle(),articleDto.getCodeArticle());
        Assertions.assertEquals(articleResult.getPrixunitaireHT(),articleDto.getPrixunitaireHT());
        Assertions.assertEquals(articleResult.getPrixunitaireTtc(),articleDto.getPrixunitaireTtc());
        Assertions.assertEquals(articleResult.getTauxTva(),articleDto.getTauxTva());

        //Assertion AssertJ
        verify(articleRepositoryMock).save(any());
        verify(articleMapperMock).articleToArticleDto(any());
        assertThat(articleResult).isNotNull();
        assertThat(articleResult.getId()).isNotNull();
        assertThat(articleResult.getDateCreation()).isNotNull();
        assertThat(articleResult.getCodeArticle()).isEqualTo(articleDto.getCodeArticle());
        assertThat(articleResult.getDateModification()).isNull();
    }

    @Test
    @DisplayName("Enregistrer Article et tester @BeforeEach")
    void save3() {
        //Arrange

        Article article = new Article();
        when(articleRepositoryMock.save(any())).thenReturn(article);
        when(articleMapperMock.articleToArticleDto(article)).thenReturn(articleDto2);
        //Act
        Article articleMock = articleRepositoryMock.save(any());
        ArticleDto articleDtoResult = articleMapperMock.articleToArticleDto(articleMock);
        ArticleDto articleResult = articleService.save(articleDtoResult);

        //Assert

        //Assertions Junit5
        Assertions.assertNotNull(articleResult);
        Assertions.assertNotNull(articleResult.getId());
        Assertions.assertNotNull(articleResult.getDateCreation());
        Assertions.assertEquals(articleResult.getCodeArticle(),articleDto2.getCodeArticle());
        Assertions.assertEquals(articleResult.getPrixunitaireHT(),articleDto2.getPrixunitaireHT());
        Assertions.assertEquals(articleResult.getPrixunitaireTtc(),articleDto2.getPrixunitaireTtc());
        Assertions.assertEquals(articleResult.getTauxTva(),articleDto2.getTauxTva());

        //Assertion AssertJ
        verify(articleRepositoryMock).save(any());
        verify(articleMapperMock).articleToArticleDto(any());
        assertThat(articleResult).isNotNull();
        assertThat(articleResult.getId()).isNotNull();
        assertThat(articleResult.getDateCreation()).isNotNull();
        assertThat(articleResult.getCodeArticle()).isEqualTo(articleDto2.getCodeArticle());
        assertThat(articleResult.getDateModification()).isNull();
    }
    @Test
    @Disabled
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByPrixunitaireTtcGreaterThan() {
    }

    @Test
    void getReferenceById() {
    }

    @Test
    void findByCodeArticleLike() {
    }

    @Test
    void findByDateCreationAfter() {
    }

    @Test
    void findByDateCreationBefore() {
    }

    @Test
    void findAll_ShoudTrow_RuntimeException() {
        //Arange
        when(articleRepositoryMock.findAll()).thenThrow(new RuntimeException());

        //Act
        try {
            List<Article> res = articleRepositoryMock.findAll();
        } catch (Exception e) {
            //assert
            Assertions.assertThrows(RuntimeException.class , ()-> articleRepositoryMock.findAll());
            verify(articleRepositoryMock , times(2)).findAll();
        }




    }

    @Test
    void findArticlesByEntreprise() {
    }

    @Test
    void findArticlesByCategorie() {
    }

    @Test
    void findArticlesByNomClient() {
    }
}