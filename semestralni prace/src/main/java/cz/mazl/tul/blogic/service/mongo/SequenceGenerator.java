package cz.mazl.tul.blogic.service.mongo;

public interface SequenceGenerator {

    long generateSequence(String seqName);
}
