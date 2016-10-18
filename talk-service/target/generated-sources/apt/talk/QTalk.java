package talk;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTalk is a Querydsl query type for Talk
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTalk extends EntityPathBase<Talk> {

    private static final long serialVersionUID = -1782859570;

    public static final QTalk talk = new QTalk("talk");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath speaker = createString("speaker");

    public final StringPath summary = createString("summary");

    public final StringPath title = createString("title");

    public QTalk(String variable) {
        super(Talk.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QTalk(Path<? extends Talk> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QTalk(PathMetadata<?> metadata) {
        super(Talk.class, metadata);
    }

}

