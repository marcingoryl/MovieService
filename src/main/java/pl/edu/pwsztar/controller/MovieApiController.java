package pl.edu.pwsztar.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.domain.dto.*;
import pl.edu.pwsztar.service.MovieService;

import java.util.List;

@Controller
@RequestMapping(value="/api")
public class MovieApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieApiController.class);

    private final MovieService movieService;

    @Autowired
    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin
    @ApiOperation(value = "Find all movies", notes = "provide information about all movies")
    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieDto>> getMovies() {
        LOGGER.info("find all movies");

        List<MovieDto> moviesDto = movieService.findAll();
        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Create new movie", notes = "add new movie do collection")
    @PostMapping(value = "/movies")
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieDto createMovieDto) {
        LOGGER.info("create movie: {}", createMovieDto);
        movieService.creatMovie(createMovieDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @ApiOperation(value = "Delete movie by id", notes = "delete movie giving id as argument")
    @DeleteMapping(value = "/movies/{movieId}")
    public ResponseEntity<Void> deleteMovie(@ApiParam(value = "unique id of movie") @PathVariable Long movieId) {
        LOGGER.info("delete movie: {}", movieId);
        movieService.deleteMovie(movieId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Find movie by id", notes = "provide information about movie details by id")
    @GetMapping(value = "/movies/{movieId}")
    public ResponseEntity<DetailsMovieDto> detailsMovie(@ApiParam(value = "unique id of movie", example = "1") @PathVariable Long movieId) {
        LOGGER.info("details movie: {}", movieId);
        DetailsMovieDto detailsMovieDto = movieService.findMovie(movieId);

        return new ResponseEntity<>(detailsMovieDto, HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Edit information about movie", notes = "edit information about movie giving its id")
    @PutMapping(value = "/movies/{movieId}")
    public ResponseEntity<Void> updateMovie(@RequestBody UpdateMovieDto updateMovieDto,
                                            @PathVariable Long movieId) {
        LOGGER.info("update movie: {}", movieId);
        movieService.updateMovie(movieId, updateMovieDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Count all available movies", notes = "number of all available movies")
    @GetMapping(value = "/movies/counter")
    public ResponseEntity<MovieCounterDto> countMovies() {
        LOGGER.info("count movies");

        MovieCounterDto movieCounterDto = movieService.countMovies();
        return new ResponseEntity<>(movieCounterDto, HttpStatus.OK);
    }

}
