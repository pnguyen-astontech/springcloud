package talk;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/talk")
@RefreshScope  //POST to :8881/refresh to load in new properties from config-server
public class TalkController {

    private TalkRepository talkRepository;

    @Value("${greeting}")
    private String greeting;

    @Autowired
    public TalkController(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    @RequestMapping("/greeting")
    String greet() {
        return greeting;
    }

    @RequestMapping(method = GET)
    List<Talk> findTalks(TalkCriteria criteria) {
        return (List<Talk>) talkRepository.findAll(criteria.toPredicate());
    }

    @RequestMapping(value = "{talk}", method = GET)
    Talk talkDetails(@PathVariable Talk talk) {
        if (talk == null) {
            throw new TalkNotFoundException();
        }
        return talk;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class TalkNotFoundException extends RuntimeException {}

    static class TalkCriteria {

        static QTalk talk = QTalk.talk;

        String name;
        String speaker;

        Predicate toPredicate() {
            BooleanBuilder builder = new BooleanBuilder();
            if (hasText(name)) {
                builder.and(talk.title.containsIgnoreCase(name));
            }
            if (speaker != null) {
                builder.and(talk.speaker.containsIgnoreCase(speaker));
            }
            return builder.getValue();
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSpeaker(String speaker) {
            this.speaker = speaker;
        }
    }

}
