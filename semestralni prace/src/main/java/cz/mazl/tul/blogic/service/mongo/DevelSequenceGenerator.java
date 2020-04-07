package cz.mazl.tul.blogic.service.mongo;

public class DevelSequenceGenerator implements SequenceGenerator {

    @Override
    public long generateSequence(String seqName) {
        return (long) (Math.random() * 100);
    }
}
