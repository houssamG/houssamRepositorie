package com.houssam.gestiondestock.controller;

import com.houssam.gestiondestock.dto.ArticleDto;
import com.houssam.gestiondestock.response.ResponseHandler;
import com.houssam.gestiondestock.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.houssam.gestiondestock.controller.ConstantsController.APP_ROOT;

@RestController
@Slf4j
@Tag(name = "Article Apis")
public class ArticleController {
    @Autowired
    @Qualifier("ArticleServiceImpl")
    ArticleService articleService;

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un Article")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "creation article OK" ,content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                            schema = @Schema(example = "{\n" +
                                    "    \"data\": {\n" +
                                    "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                                    "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                                    "        \"codeArticle\": \"article4\",\n" +
                                    "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                                    "        \"prixunitaireHT\": 55,\n" +
                                    "        \"tauxTva\": 20,\n" +
                                    "        \"prixunitaireTtc\": 77,\n" +
                                    "        \"photo\": \"https://photo.com\"\n" +
                                    "    },\n" +
                                    "    \"message\": \"L'article a bien été Crée!\",\n" +
                                    "    \"status\": 201\n" +
                                    "}" )
                    )
            }), @ApiResponse(responseCode = "500", description = "erreur interne du serveur !" ,content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": null,\n" +
                            "    \"message\": \"L'article n'a pas été Crée!\",\n" +
                            "    \"status\": 500\n" +
                            "}")
            )
    }) ,@ApiResponse(responseCode = "400", description = "champs requete non valide" ,content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"prametre1\": \"Le prametre1 est obligatoire !\"\n" +
                            "}")
            )
    })
    }
    )
    ResponseEntity<Object> save(@Valid @RequestBody ArticleDto articleDto) {

        ArticleDto res = null;
        try {
            res = articleService.save(articleDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseHandler.generateResponse("L'article n'a pas été Crée!" ,HttpStatus.INTERNAL_SERVER_ERROR , res);
        }
        return ResponseHandler.generateResponse("L'article a bien été Crée!" ,HttpStatus.CREATED , res);
    }

    @PutMapping(value = APP_ROOT + "/articles/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier un Article")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "modification article OK" ,content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                            schema = @Schema(example = "{\n" +
                                    "    \"data\": {\n" +
                                    "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                                    "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                                    "        \"codeArticle\": \"article4\",\n" +
                                    "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                                    "        \"prixunitaireHT\": 55,\n" +
                                    "        \"tauxTva\": 20,\n" +
                                    "        \"prixunitaireTtc\": 77,\n" +
                                    "        \"photo\": \"https://photo.com\"\n" +
                                    "    },\n" +
                                    "    \"message\": \"L'article a bien été Modifié!\",\n" +
                                    "    \"status\": 200\n" +
                                    "}" )
                    )
            }), @ApiResponse(responseCode = "500", description = "erreur interne du serveur !" ,content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": null,\n" +
                            "    \"message\": \"L'article n'a pas été Modifié!\",\n" +
                            "    \"status\": 500\n" +
                            "}")
            )
    }) ,@ApiResponse(responseCode = "400", description = "champs requete non valide" ,content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"prametre1\": \"Le prametre1 est obligatoire !\"\n" +
                            "}")
            )
    })
    }
    )
    ResponseEntity<Object> update(@Valid @RequestBody ArticleDto articleDto) {

        ArticleDto res = null;
        try {
            res = articleService.update(articleDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseHandler.generateResponse("L'article n'a pas été Modifié!" ,HttpStatus.INTERNAL_SERVER_ERROR , res);
        }
        return ResponseHandler.generateResponse("L'article a bien été Modifié!" ,HttpStatus.OK , res);
    }

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un article en passant son id en parametre" )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200" , description = "Suppression Ok" , content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE , schema = @Schema(example = "{\n" +
                                    "    \"data\": null,\n" +
                                    "    \"message\": \"L'article a bien été supprimé.\",\n" +
                                    "    \"status\": 200\n" +
                                    "}"))
                    }),
                    @ApiResponse(responseCode = "500" ,description = "Erreur serveur interne", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE , schema = @Schema(example = "{\n" +
                                    "    \"data\": null,\n" +
                                    "    \"message\": \"L'article n'a pas été supprimée!\",\n" +
                                    "    \"status\": 500\n" +
                                    "}"))
                    }),
                    @ApiResponse(responseCode = "400" ,description = "Parametre non valid", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE , schema = @Schema(example = "{\n" +
                                    "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                                    "    \"status\": 400,\n" +
                                    "    \"error\": \"Bad Request\",\n" +
                                    "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                                    "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                                    "}"))
                    })
            }
    )
    ResponseEntity<Object> delete(@Parameter(description = "C'est l'id de l'article a supprimer") @PathVariable(value = "idArticle") Integer id) {

        try {
            articleService.delete(id);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("L'article n'a pas été supprimé." , HttpStatus.INTERNAL_SERVER_ERROR , null);
        }
        return ResponseHandler.generateResponse("L'article a bien été supprimé." , HttpStatus.OK , null);
    }

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Aficher tous les articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles trouvées"
            ,content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": {\n" +
                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"codeArticle\": \"article4\",\n" +
                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                            "        \"prixunitaireHT\": 55,\n" +
                            "        \"tauxTva\": 20,\n" +
                            "        \"prixunitaireTtc\": 77,\n" +
                            "        \"photo\": \"https://photo.com\"\n" +
                            "    },\n" +
                            "    \"message\": \"Articles Trouvées\",\n" +
                            "    \"status\": 302\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "500" , description = "erreur serveur interne",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                            schema = @Schema(example = "{\n" +
                                    "    \"data\": null,\n" +
                                    "    \"message\": \"Erreur recherche articles\",\n" +
                                    "    \"status\": 500\n" +
                                    "}"))
            })
    })
    ResponseEntity<List<Object>> findAll() {

        List<ArticleDto> articleDtos = null;
        try {
            articleDtos = articleService.findAll();
        } catch (Exception e) {
            return ResponseHandler.generateListResponse("Erreur recherche articles" ,HttpStatus.INTERNAL_SERVER_ERROR , Collections.singletonList(articleDtos));
        }
        if(articleDtos.size()>0){
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articleDtos));

        }else {
            return ResponseHandler.generateListResponse("Aucun articles trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articleDtos));

        }
        
    }

    @GetMapping(value = APP_ROOT + "/articles/filterByPrixTtc/{prixTtc}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher les articles avec un prix superieur a un montant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles Touvées" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                            schema = @Schema(example = "{\n" +
                                    "    \"data\": {\n" +
                                    "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                                    "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                                    "        \"codeArticle\": \"article4\",\n" +
                                    "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                                    "        \"prixunitaireHT\": 55,\n" +
                                    "        \"tauxTva\": 20,\n" +
                                    "        \"prixunitaireTtc\": 77,\n" +
                                    "        \"photo\": \"https://photo.com\"\n" +
                                    "    },\n" +
                                    "    \"message\": \"Articles Trouvées\",\n" +
                                    "    \"status\": 302\n" +
                                    "}") )
            }),
            @ApiResponse(responseCode = "500" , description = "erreur serveur interne" 
                    , content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": null,\n" +
                            "    \"message\": \"Erreur recherche articles\",\n" +
                            "    \"status\": 500\n" +
                            "}"))
            } ),
            @ApiResponse(responseCode = "400" , description = "parametre non valid" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Bad Request\",\n" +
                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                            "}"))
            })
    })
    ResponseEntity<List<Object>> getArticleSuperieurAprixTtcQueryTest(@Parameter(description = "Le prix Ttc de l'article") @PathVariable BigDecimal prixTtc) {
        List<ArticleDto> articles = articleService.findByPrixunitaireTtcGreaterThan(prixTtc);
        if (articles.size() > 0) {
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articles));
        } else
            return ResponseHandler.generateListResponse("Articles Non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articles));

    }

    @GetMapping(value = APP_ROOT + "/articles/{idIarticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par son identifient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles trouveés" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": {\n" +
                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"codeArticle\": \"article4\",\n" +
                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                            "        \"prixunitaireHT\": 55,\n" +
                            "        \"tauxTva\": 20,\n" +
                            "        \"prixunitaireTtc\": 77,\n" +
                            "        \"photo\": \"https://photo.com\"\n" +
                            "    },\n" +
                            "    \"message\": \"Articles Trouvées\",\n" +
                            "    \"status\": 302\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "500" , description = "erreur serveur interne" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": null,\n" +
                            "    \"message\": \"Erreur recherche article\",\n" +
                            "    \"status\": 500\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400" , description = "parametre non valid" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Bad Request\",\n" +
                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                            "}"))
            })
    })
    ResponseEntity<Object> getReferenceById(@Parameter(description = "L'id de l'article") @PathVariable("idIarticle") Integer id) {
        ArticleDto article = null;
        try {
            article = articleService.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            return ResponseHandler.generateResponse("L'article n'a pas été trouvé." , HttpStatus.NOT_FOUND , article);
        }
        return ResponseHandler.generateResponse("L'article a été trouvé." , HttpStatus.FOUND , article);
    }

    @GetMapping(value = APP_ROOT + "/articles/code/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles trouveés" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": {\n" +
                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"codeArticle\": \"article4\",\n" +
                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                            "        \"prixunitaireHT\": 55,\n" +
                            "        \"tauxTva\": 20,\n" +
                            "        \"prixunitaireTtc\": 77,\n" +
                            "        \"photo\": \"https://photo.com\"\n" +
                            "    },\n" +
                            "    \"message\": \"Articles Trouvées\",\n" +
                            "    \"status\": 302\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "500" , description = "erreur serveur interne" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": null,\n" +
                            "    \"message\": \"Erreur recherche article\",\n" +
                            "    \"status\": 500\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400" , description = "parametre non valid" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Bad Request\",\n" +
                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                            "}"))
            })
    })
    ResponseEntity<List<Object>> findByCodeArticleLike(@Parameter(description = "Le code de l'article") @PathVariable String codeArticle) {
        List<ArticleDto> articles = articleService.findByCodeArticleLike("%" + codeArticle + "%");
        if (articles.size() > 0) {
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articles));
        } else
            return ResponseHandler.generateListResponse("Articles non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articles));
    }

    @GetMapping(value = APP_ROOT + "/articles/dateCreationAfter", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article avec une date de creation apres une date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles Trouveés" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"data\": {\n" +
                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                            "        \"codeArticle\": \"article4\",\n" +
                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                            "        \"prixunitaireHT\": 55,\n" +
                            "        \"tauxTva\": 20,\n" +
                            "        \"prixunitaireTtc\": 77,\n" +
                            "        \"photo\": \"https://photo.com\"\n" +
                            "    },\n" +
                            "    \"message\": \"Articles Trouvées\",\n" +
                            "    \"status\": 302\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "500" , description = "internal error server" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE , 
                    schema = @Schema(example = "{\n" +
                            "    \"data\": null,\n" +
                            "    \"message\": \"Erreur recherche article\",\n" +
                            "    \"status\": 500\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "parametre non valid" ,
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                    schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Bad Request\",\n" +
                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                            "}"))
            })
    })
    ResponseEntity<List<Object>> findByDateCreationAfter(@Parameter(description = "La date de creation de l'article") @RequestParam Instant dateCreation) {
        List<ArticleDto> articles = articleService.findByDateCreationAfter(dateCreation);
        if (articles.size() > 0) {
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articles));
        } else
            return ResponseHandler.generateListResponse("Articles non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articles));
    }

    @GetMapping(value = APP_ROOT + "/articles/dateCreationBefore", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article avec une date de creation avant une date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles Trouveés" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": {\n" +
                                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"codeArticle\": \"article4\",\n" +
                                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                                            "        \"prixunitaireHT\": 55,\n" +
                                            "        \"tauxTva\": 20,\n" +
                                            "        \"prixunitaireTtc\": 77,\n" +
                                            "        \"photo\": \"https://photo.com\"\n" +
                                            "    },\n" +
                                            "    \"message\": \"Articles Trouvées\",\n" +
                                            "    \"status\": 302\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "500" , description = "internal error server" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": null,\n" +
                                            "    \"message\": \"Erreur recherche article\",\n" +
                                            "    \"status\": 500\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "400", description = "parametre non valid" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                                            "    \"status\": 400,\n" +
                                            "    \"error\": \"Bad Request\",\n" +
                                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                                            "}"))
                    })
    })
    ResponseEntity<List<Object>> findByDateCreationBefore(@Parameter(description = "La date de création de l'article") @RequestParam Instant dateCreation) {
        List<ArticleDto> articles = articleService.findByDateCreationBefore(dateCreation);
        if (articles.size() > 0) {
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articles));
        } else
            return ResponseHandler.generateListResponse("Articles non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articles));
    }

    @GetMapping(value = APP_ROOT + "/articles/byEntreprise/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher les articles d'une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles Trouveés" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": {\n" +
                                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"codeArticle\": \"article4\",\n" +
                                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                                            "        \"prixunitaireHT\": 55,\n" +
                                            "        \"tauxTva\": 20,\n" +
                                            "        \"prixunitaireTtc\": 77,\n" +
                                            "        \"photo\": \"https://photo.com\"\n" +
                                            "    },\n" +
                                            "    \"message\": \"Articles Trouvées\",\n" +
                                            "    \"status\": 302\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "500" , description = "internal error server" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": null,\n" +
                                            "    \"message\": \"Erreur recherche article\",\n" +
                                            "    \"status\": 500\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "400", description = "parametre non valid" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                                            "    \"status\": 400,\n" +
                                            "    \"error\": \"Bad Request\",\n" +
                                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                                            "}"))
                    })
    })
    ResponseEntity<List<Object>> findArticlesByEntreprise(@Parameter(description = "L'id de l'entreprise") @PathVariable Integer idEntreprise) {
        List<ArticleDto> articles = articleService.findArticlesByEntreprise(idEntreprise);
        if (articles.size() > 0) {
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articles));
        } else
            return ResponseHandler.generateListResponse("Articles non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articles));
    }

    @GetMapping(value = APP_ROOT + "/articles/byCategorie/{idCategorie}")
    @Operation(summary = "Rechercher les articles d'une categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles Trouveés" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": {\n" +
                                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"codeArticle\": \"article4\",\n" +
                                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                                            "        \"prixunitaireHT\": 55,\n" +
                                            "        \"tauxTva\": 20,\n" +
                                            "        \"prixunitaireTtc\": 77,\n" +
                                            "        \"photo\": \"https://photo.com\"\n" +
                                            "    },\n" +
                                            "    \"message\": \"Articles Trouvées\",\n" +
                                            "    \"status\": 302\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "500" , description = "internal error server" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": null,\n" +
                                            "    \"message\": \"Erreur recherche article\",\n" +
                                            "    \"status\": 500\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "400", description = "parametre non valid" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                                            "    \"status\": 400,\n" +
                                            "    \"error\": \"Bad Request\",\n" +
                                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                                            "}"))
                    })
    })
    ResponseEntity<List<Object>> findArticlesByCategorie(@Parameter(description = "L'id de la categorie") @PathVariable Integer idCategorie) {
        List<ArticleDto> articles = articleService.findArticlesByCategorie(idCategorie);
        if (articles.size() > 0) {
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articles));
        } else
            return ResponseHandler.generateListResponse("Articles non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articles));
    }

    @GetMapping(value = APP_ROOT + "/articles/byNomClient/{nomClient}")
    @Operation(summary = "Rechercher les articles des clients qui ont un certain nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302" , description = "Articles Trouveés" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": {\n" +
                                            "        \"dateCreation\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"dateModification\": \"2024-06-03T09:31:00Z\",\n" +
                                            "        \"codeArticle\": \"article4\",\n" +
                                            "        \"designation\": \"Ce ci est un test darticle 4\",\n" +
                                            "        \"prixunitaireHT\": 55,\n" +
                                            "        \"tauxTva\": 20,\n" +
                                            "        \"prixunitaireTtc\": 77,\n" +
                                            "        \"photo\": \"https://photo.com\"\n" +
                                            "    },\n" +
                                            "    \"message\": \"Articles Trouvées\",\n" +
                                            "    \"status\": 302\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "500" , description = "internal error server" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"data\": null,\n" +
                                            "    \"message\": \"Erreur recherche article\",\n" +
                                            "    \"status\": 500\n" +
                                            "}"))
                    }),
            @ApiResponse(responseCode = "400", description = "parametre non valid" ,
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE ,
                                    schema = @Schema(example = "{\n" +
                                            "    \"timestamp\": \"2024-06-03T14:40:52.494+00:00\",\n" +
                                            "    \"status\": 400,\n" +
                                            "    \"error\": \"Bad Request\",\n" +
                                            "    \"message\": \"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; For input string: \\\"dd\\\"\",\n" +
                                            "    \"path\": \"/gestiondestock/v1/articles/delete/dd\"\n" +
                                            "}"))
                    })
    })
    ResponseEntity<List<Object>> findArticlesByNomClient(@Parameter(description = "Le nom du client") @PathVariable String nomClient) {
        List<ArticleDto> articles = articleService.findArticlesByNomClient(nomClient);
        if (articles.size() > 0) {
            return ResponseHandler.generateListResponse("Articles Trouvées" ,HttpStatus.FOUND , Collections.singletonList(articles));
        } else
            return ResponseHandler.generateListResponse("Articles non Trouvées" ,HttpStatus.NOT_FOUND , Collections.singletonList(articles));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
