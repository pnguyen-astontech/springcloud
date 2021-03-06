package review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewRepository repository;

    @Autowired
    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = GET)
    List<Review> forTalk(@RequestParam long talkId) {
        return repository.findByTalkId(talkId);
    }

    @RequestMapping(value = "{review}", method = GET)
    Review reviewDetails(@PathVariable Review review) {
        if (review == null) {
            throw new ReviewNotFoundException();
        }
        return review;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class ReviewNotFoundException extends RuntimeException { }
}
